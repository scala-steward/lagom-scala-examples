package akka.persistence.typed.scaladsl

import akka.NotUsed
import akka.cluster.sharding.typed.scaladsl.EntityTypeKey
import akka.persistence.query.Offset
import akka.stream.{ FlowShape, Graph }
import com.lightbend.lagom.scaladsl.api.broker.Topic
import com.lightbend.lagom.scaladsl.broker.TopicProducer
import com.lightbend.lagom.scaladsl.persistence.{ AggregateEvent, AggregateEventShards, EventStreamElement, PersistentEntityRegistry }

/**
 * @since May 09, 2021
 * @author <a href="mailto:michael@ahlers.consulting">Michael Ahlers</a>
 */
class EventSourcedTopicProducerRegistry[Event <: AggregateEvent[Event]](
  persistentEntityRegistry: PersistentEntityRegistry,
  eventTag: AggregateEventShards[Event])
  extends EventSourcedTopicProducer[Event]:

  def topicVia[Message](flow: Graph[FlowShape[EventStreamElement[Event], (Message, Offset)], NotUsed]): Topic[Message] =
    TopicProducer.taggedStreamWithOffset(eventTag) {
      (tag, fromOffset) =>
        persistentEntityRegistry
          .eventStream(tag, fromOffset)
          .via(flow)
    }
