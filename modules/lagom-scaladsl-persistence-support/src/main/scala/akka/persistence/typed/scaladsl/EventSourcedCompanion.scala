package akka.persistence.typed.scaladsl

import akka.NotUsed
import akka.cluster.sharding.typed.ShardingEnvelope
import akka.cluster.sharding.typed.scaladsl.{ ClusterSharding, Entity, EntityContext, EntityTypeKey }
import akka.persistence.typed.PersistenceId
import akka.persistence.typed.scaladsl.EventSourcedCompanion.ToEntityId
import com.lightbend.lagom.scaladsl.persistence.{ AggregateEvent, AggregateEventShards, AkkaTaggerAdapter, PersistentEntityRegistry }

/**
 * @since May 09, 2021
 * @author <a href="mailto:michael@ahlers.consulting">Michael Ahlers</a>
 */
trait EventSourcedCompanion[Identity: ToEntityId]:

  type Environment

  type Command
  type Reply
  type Event <: AggregateEvent[Event]
  type State

  type Behavior = EventSourcedBehavior[Command, Event, State]
  type References = EventSourcedReferenceFactory[Identity, Command]
  type Topics = EventSourcedTopicProducer[Event]

  def typeKey: EntityTypeKey[Command]
  def eventTag: AggregateEventShards[Event]

  def initialState: State

  def applyCommand: Environment => State => Command => ReplyEffect[Event, State]

  def applyEvent: State => Event => State

  final def apply(persistenceId: PersistenceId, state: State)(using environment: Environment): Behavior =
    EventSourcedBehavior
      .withEnforcedReplies(
        persistenceId,
        state,
        applyCommand(environment)(_)(_),
        applyEvent(_)(_))

  final def apply(persistenceId: PersistenceId)(using environment: Environment): Behavior =
    apply(persistenceId, initialState)

  final def apply(entityContext: EntityContext[Command], state: State)(using environment: Environment): Behavior =
    apply(PersistenceId(entityContext.entityTypeKey.name, entityContext.entityId), state)
      .withTagger(AkkaTaggerAdapter.fromLagom(entityContext, eventTag))

  final def apply(entityContext: EntityContext[Command])(using environment: Environment): Behavior =
    apply(entityContext, initialState)

  final def apply(identity: Identity, state: State)(using environment: Environment): Behavior =
    apply(
      PersistenceId(
        typeKey.name,
        summon[ToEntityId[Identity]].apply(identity)),
      state)

  final def apply(identity: Identity)(using environment: Environment): Behavior =
    apply(identity, initialState)

  final def entity(using environment: Environment): Entity[Command, ShardingEnvelope[Command]] =
    Entity(typeKey)(apply(_))

  final def entity()(using evidence: NotUsed =:= Environment): Entity[Command, ShardingEnvelope[Command]] =
    /** @todo Figure out correct usage. */
    entity(using evidence(NotUsed))

  final def references(clusterSharding: ClusterSharding): References =
    EventSourcedReferenceFactoryForClusterSharding(clusterSharding, typeKey)

  final def topics(persistentEntityRegistry: PersistentEntityRegistry): Topics =
    EventSourcedTopicProducerForEntityRegistry(persistentEntityRegistry, eventTag)

object EventSourcedCompanion:

  trait ToEntityId[Identity] extends (Identity => String)
