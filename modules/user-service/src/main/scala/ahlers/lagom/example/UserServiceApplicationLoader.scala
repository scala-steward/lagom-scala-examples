package ahlers.lagom.example

import com.lightbend.lagom.scaladsl.server.{LagomApplicationContext, LagomApplicationLoader}
import play.api.Logger

/**
 * @author <a href="mailto:michael@ahlers.consulting">Michael Ahlers</a>
 * @since April 22, 2021
 */
class UserServiceApplicationLoader extends LagomApplicationLoader:

  private val logger = Logger(classOf[UserServiceApplicationLoader])

  logger.info(s"Starting User Service application…")

  override def load(context: LagomApplicationContext) =
    new UserServiceLiveApplication(context)

  override def loadDevMode(context: LagomApplicationContext) =
    new UserServiceTestApplication(context)

  override def describeService =
    Some(UserServiceApi.descriptor)
