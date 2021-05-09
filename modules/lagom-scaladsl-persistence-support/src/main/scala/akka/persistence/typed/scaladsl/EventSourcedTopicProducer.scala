package akka.persistence.typed.scaladsl

import akka.NotUsed
import akka.persistence.query.Offset
import akka.stream.{ FlowShape, Graph }
import com.lightbend.lagom.scaladsl.api.broker.Topic
import com.lightbend.lagom.scaladsl.persistence.{ AggregateEvent, EventStreamElement }

/**
 * @since May 09, 2021
 * @author <a href="mailto:michael@ahlers.consulting">Michael Ahlers</a>
 */
trait EventSourcedTopicProducer[Event <: AggregateEvent[Event]]:

  def topicVia[Message](flow: Graph[FlowShape[EventStreamElement[Event], (Message, Offset)], NotUsed]): Topic[Message]

