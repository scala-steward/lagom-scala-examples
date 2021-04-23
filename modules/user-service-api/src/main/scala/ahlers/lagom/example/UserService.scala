package ahlers.lagom.example

import akka.NotUsed
import com.devskiller.friendly_id.FriendlyId
import com.lightbend.lagom.scaladsl.api.transport.Method
import com.lightbend.lagom.scaladsl.api.{ Descriptor, Service, ServiceCall }
import com.lightbend.lagom.scaladsl.api.ServiceSupport
import com.lightbend.lagom.scaladsl.api.deser.PathParamSerializer
import play.api.libs.json.{ Format, Json }
import com.devskiller.friendly_id.FriendlyId
import com.lightbend.lagom.scaladsl.api.deser.PathParamSerializer
import play.api.libs.functional.syntax._

import java.util.UUID

/**
 * @author <a href="mailto:michael@ahlers.consulting">Michael Ahlers</a>
 * @since April 08, 2021
 */
trait UserService extends Service {

  import UserService._

  def getUserView(userId: UserId): ServiceCall[NotUsed, UserView]

  override val descriptor = {
    import Method.GET
    import Service.{ named, restCall }

    named("user-service")
      .withCalls(
        restCall(GET, "/users/:userId", getUserView _))
      .withAutoAcl(true)
  }

}

object UserService {

  implicit val formatUserId: Format[UserId] =
    Format.of[String]
      .inmap[UUID](FriendlyId.toUuid, FriendlyId.toFriendlyId)
      .inmap[UserId](UserId.apply, _.toUuid)

  implicit val formatUserView: Format[UserView] =
    Json.format

  implicit val pathParamSerializerUserId: PathParamSerializer[UserId] =
    PathParamSerializer.required[UserId]("UserId")((UserId.apply _).compose(FriendlyId.toUuid))((FriendlyId.toFriendlyId _).compose(_.toUuid))

  /** Scala 2 macros cannot be used in Scala 3 modules, so this effectively does the work of [[com.lightbend.lagom.scaladsl.server.LagomApplicationLoader#readDescriptor]], albeit manually. */
  val descriptor: Descriptor =
    new UserService {
      override def getUserView(userId: UserId) = ???
    }.descriptor

}
