package ahlers.lagom.examples

import com.lightbend.lagom.scaladsl.broker.kafka.LagomKafkaComponents
import com.lightbend.lagom.scaladsl.devmode.LagomDevModeComponents
import com.lightbend.lagom.scaladsl.server.{LagomApplication, LagomApplicationContext, LagomServerComponents}
import com.lightbend.lagom.scaladsl.akka.discovery.AkkaDiscoveryComponents
import com.lightbend.lagom.scaladsl.client.CircuitBreakerComponents
import com.lightbend.lagom.scaladsl.playjson.EmptyJsonSerializerRegistry
import play.api.libs.ws.ahc.AhcWSComponents
import com.lightbend.lagom.scaladsl.server.LagomServer
import com.lightbend.lagom.scaladsl.server.LagomServiceBinder
import com.lightbend.lagom.scaladsl.server.LagomServiceBinding
import com.lightbend.lagom.scaladsl.persistence.jdbc.JdbcPersistenceComponents
import com.lightbend.lagom.scaladsl.persistence.slick.SlickPersistenceComponents
import org.slf4j.LoggerFactory
import play.api.Logger
import play.api.db.HikariCPComponents

/**
 * @author <a href="mailto:michael@ahlers.consulting">Michael Ahlers</a>
 * @since April 22, 2021
 */
abstract class UserServiceApplication(
  context: LagomApplicationContext)
  extends LagomApplication(context)
    with AhcWSComponents
    with CircuitBreakerComponents
    with JdbcPersistenceComponents
    with HikariCPComponents
    with LagomServerComponents
    with SlickPersistenceComponents:

  private val logger = Logger(classOf[UserServiceApplication])

  logger.info(s"Started User Service application.")

  override lazy val jsonSerializerRegistry =
    UserImportSerializerRegistry(actorSystem)

  clusterSharding.init(UserImportEntity.entity())
  lazy val userImportRegistry = UserImportEntity.registry(clusterSharding)

  override lazy val lagomServer =
    val service =
      UserServiceDefault(
        userImportRegistry,
        actorSystem)

    val binder = LagomServiceBinder[UserService](lagomServerBuilder, service.descriptor)
    LagomServer.forService(binder.to(service))

  /**
   * Allows subscription to this service's own events.
   * @see [[https://groups.google.com/d/msg/lagom-framework/K59onuKGYkw/zZiZkTVgAAAJ]]
   */
  lazy val userService = UserService.implementWith(serviceClient)

class UserServiceTestApplication(
  context: LagomApplicationContext)
  extends UserServiceApplication(context)
    with LagomDevModeComponents
    with LagomKafkaComponents

class UserServiceLiveApplication(
  context: LagomApplicationContext)
  extends UserServiceApplication(context)
    with AkkaDiscoveryComponents
    with LagomKafkaComponents
