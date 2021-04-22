package ahlers.lagom.example

import com.lightbend.lagom.scaladsl.api.Service

/**
 * @author <a href="mailto:michael@ahlers.consulting">Michael Ahlers</a>
 * @since April 08, 2021
 */
trait UserServiceApi extends Service:
  override val descriptor = UserServiceApi.descriptor

object UserServiceApi:
  val descriptor = {
    import Service.*
    named("user-service")
      .withAutoAcl(true)
  }
