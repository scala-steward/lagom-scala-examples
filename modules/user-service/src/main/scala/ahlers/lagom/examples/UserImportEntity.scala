package ahlers.lagom.examples

import akka.NotUsed
import akka.cluster.sharding.typed.scaladsl.EntityTypeKey
import akka.persistence.typed.scaladsl.{ Effect, EventSourcedCompanion }
import akka.persistence.typed.scaladsl.EventSourcedCompanion.ToEntityId
import com.lightbend.lagom.scaladsl.persistence.{ AggregateEvent, AggregateEventTag }
import ahlers.lagom.examples.UserImportEntity.Command
import ahlers.lagom.examples.UserImportEntity.Event
import UserImportEntity.given_ToEntityId_UserImportId

/**
 * @since May 09, 2021
 * @author <a href="mailto:michael@ahlers.consulting">Michael Ahlers</a>
 */
object UserImportEntity extends EventSourcedCompanion[UserImportId]:

  given ToEntityId[UserImportId] with
    def apply(x: UserImportId) = "%d".format(x)

  type Environment = NotUsed

  sealed trait Command

  sealed trait Reply

  sealed trait Event extends AggregateEvent[Event]:
    override final val aggregateTag = eventTag

  sealed trait State
  object State:
    case object ImportNotRunning extends State

  override val typeKey = EntityTypeKey("user-import-entity_v1")
  override val eventTag = AggregateEventTag.sharded[Event]("user-import-event_v1_tag", numShards = 10)

  override val initialState = State.ImportNotRunning

  override val applyCommand =
    environment =>
      command =>
        ???

  override val applyEvent =
    state =>
      event =>
        ???
