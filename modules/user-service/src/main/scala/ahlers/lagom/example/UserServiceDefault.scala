package ahlers.lagom.example

import akka.actor.ActorSystem
import com.lightbend.lagom.scaladsl.api.ServiceCall

import scala.concurrent.Future

/**
 * @author <a href="mailto:michael@ahlers.consulting">Michael Ahlers</a>
 * @since April 22, 2021
 */
class UserServiceDefault(
  actorSystem: ActorSystem)
  extends UserService:

  override def getUserView(userId: UserId) =
    ServiceCall { request =>
      Future.successful(UserView(userId, s"User $userId"))
    }
