package ahlers.lagom.examples

import akka.actor.ActorSystem
import akka.actor.typed.ActorRef
import akka.actor.typed.ActorRefResolver
import akka.actor.typed.scaladsl.adapter._
import akka.serialization.SerializationSetup
import com.lightbend.lagom.scaladsl.playjson.{ JsonSerializer, JsonSerializerRegistry }
import play.api.libs.functional.syntax._
import play.api.libs.json._

/**
 * @since May 09, 2021
 * @author <a href="mailto:michael@ahlers.consulting">Michael Ahlers</a>
 */
class UserImportSerializerRegistry(actorRefResolver: => ActorRefResolver) extends JsonSerializerRegistry:

  import UserImportEntity.Command
  import UserImportEntity.Command._
  import UserImportEntity.Reply
  import UserImportEntity.State
  import UserImportEntity.State._

  private given[A]: Format[ActorRef[A]] =
    Format.of[String]
      .inmap(
        actorRefResolver.resolveActorRef(_),
        actorRefResolver.toSerializationFormat(_))

  private given Format[GetUserImport] =
    (__ \ "replyTo").format[ActorRef[State]]
      .inmap(GetUserImport(_), _.replyTo)

  override val serializers =
    JsonSerializer[GetUserImport] ::
      //JsonSerializer(emptySingletonFormat(ImportNotRunning)) ::
      Nil

object UserImportSerializerRegistry:

  /** Convenience constructor converting to an [[ActorRefResolver]]. */
  def apply(actorSystem: => ActorSystem): UserImportSerializerRegistry =
    new UserImportSerializerRegistry(ActorRefResolver(actorSystem.toTyped))

  /** Supports testing. */
  val serializationSetup: SerializationSetup =
    SerializationSetup(actorSystem =>
      Seq(JsonSerializerRegistry
        .serializerDetailsFor(
          actorSystem,
          UserImportSerializerRegistry(actorSystem))))
