package akka.persistence.typed.scaladsl

import akka.NotUsed
import akka.cluster.sharding.typed.scaladsl.EntityRef
import akka.persistence.query.Offset
import akka.stream.{ FlowShape, Graph }
import com.lightbend.lagom.scaladsl.api.broker.Topic
import com.lightbend.lagom.scaladsl.persistence.EventStreamElement

/**
 * @since May 09, 2021
 * @author <a href="mailto:michael@ahlers.consulting">Michael Ahlers</a>
 */
trait EventSourcedRegistry[Identity, Command]:
  def entityRef(identity: Identity): EntityRef[Command]
