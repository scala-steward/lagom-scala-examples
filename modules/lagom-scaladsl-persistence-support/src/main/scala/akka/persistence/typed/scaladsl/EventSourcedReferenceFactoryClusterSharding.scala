package akka.persistence.typed.scaladsl

import akka.cluster.sharding.typed.scaladsl.{ ClusterSharding, EntityRef, EntityTypeKey }
import akka.persistence.typed.scaladsl.EventSourcedCompanion.ToEntityId

/**
 * @since May 09, 2021
 * @author <a href="mailto:michael@ahlers.consulting">Michael Ahlers</a>
 */
private[scaladsl] class EventSourcedReferenceFactoryClusterSharding[Identity: ToEntityId, Command](
  clusterSharding: ClusterSharding,
  typeKey: EntityTypeKey[Command])
  extends EventSourcedReferenceFactory[Identity, Command]:

  override final def entityRef(identity: Identity): EntityRef[Command] =
    clusterSharding
      .entityRefFor(
        typeKey,
        summon[ToEntityId[Identity]].apply(identity))
