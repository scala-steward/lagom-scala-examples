package ahlers.lagom.examples

import akka.NotUsed
import akka.actor.ActorSystem
import akka.util.Timeout
import com.lightbend.lagom.scaladsl.api.ServiceCall
import com.lightbend.lagom.scaladsl.api.transport.NotFound

import scala.concurrent.duration._
import scala.concurrent.Future

/**
 * @author <a href="mailto:michael@ahlers.consulting">Michael Ahlers</a>
 * @since April 22, 2021
 */
class UserServiceDefault(
  userImportRegistry: UserImportRegistry,
  actorSystem: ActorSystem)
  extends UserService:

  override def getUserImportView(userImportId: UserImportId) = {
    import actorSystem.dispatcher
    import UserImportEntity.Command._
    import UserImportEntity.State._

    given Timeout = Timeout(5.seconds)

    ServiceCall { request =>
      userImportRegistry
        .entityRef(userImportId)
        .ask(GetUserImport(_))
        .map {
          case ImportNotRunning =>
            throw NotFound(s"Import $userImportId hasn't been started.")
        }
    }
  }

  override def getUserView(userId: UserId) =
    ServiceCall { request =>
      Future.successful(UserView(userId, s"User $userId"))
    }
