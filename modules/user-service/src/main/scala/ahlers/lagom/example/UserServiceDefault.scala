package ahlers.lagom.example

import akka.actor.ActorSystem

/**
 * @author <a href="mailto:michael@ahlers.consulting">Michael Ahlers</a>
 * @since April 22, 2021
 */
class UserServiceDefault(
  actorSystem: ActorSystem)
  extends UserServiceApi
