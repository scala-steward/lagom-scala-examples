package ahlers.lagom.examples

import akka.NotUsed
import akka.cluster.sharding.typed.scaladsl.EntityTypeKey
import akka.persistence.typed.scaladsl.{Effect, EventSourcedCompanion}
import akka.persistence.typed.scaladsl.EventSourcedCompanion.ToEntityId
import com.lightbend.lagom.scaladsl.persistence.{AggregateEvent, AggregateEventTag}
import ahlers.lagom.examples.UserImportEntity.Command
import ahlers.lagom.examples.UserImportEntity.Event
import UserImportEntity.given_ToEntityId_UserImportId
import akka.actor.typed.ActorRef

/**
 * @since May 09, 2021
 * @author <a href="mailto:michael@ahlers.consulting">Michael Ahlers</a>
 */
object UserImportEntity extends EventSourcedCompanion[UserImportId]:

  given ToEntityId[UserImportId] with
    def apply(x: UserImportId) = "%d".format(x)

  type Environment = NotUsed

  sealed trait Command
  object Command:
    case class GetUserImport(replyTo: ActorRef[State]) extends Command
    case class StartUserImport(replyTo: ActorRef[State]) extends Command

  sealed trait Reply

  sealed trait Event extends AggregateEvent[Event]:
    override final val aggregateTag = eventTag
  object Event:
    case object UserImportStarted extends Event

  sealed trait State
  object State:
    case object ImportNotRunning extends State
    case object ImportInProgress extends State

  import Command._
  import Event._
  import State._

  override val typeKey = EntityTypeKey("user-import-entity_v1")
  override val eventTag = AggregateEventTag.sharded[Event]("user-import-event_v1_tag", numShards = 10)

  override val initialState = ImportNotRunning

  override val applyCommand =
    environment =>

      case ImportNotRunning =>
        case command: GetUserImport =>
          Effect.reply(command.replyTo)(ImportNotRunning)
        case command: StartUserImport =>
          val event = UserImportStarted
          Effect
            .persist(event)
            .thenReply(command.replyTo)(identity)

      case ImportInProgress =>
        case command: GetUserImport =>
          Effect.reply(command.replyTo)(ImportInProgress)
        case command: StartUserImport =>
          ???

  override val applyEvent =

    case ImportNotRunning =>
      case UserImportStarted =>
        ImportInProgress

    case ImportInProgress =>
      case UserImportStarted =>
        ???

type UserImportRegistry = UserImportEntity.Registry
type UserImportTopicProducer = UserImportEntity.TopicProducer
