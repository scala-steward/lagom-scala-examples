package ahlers.lagom.example

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

  override lazy val jsonSerializerRegistry =
    EmptyJsonSerializerRegistry

  override lazy val lagomServer =
    val service = UserServiceDefault(actorSystem)
    val binder = LagomServiceBinder[UserServiceApi](lagomServerBuilder, UserServiceApi.descriptor)
    LagomServer.forService(binder.to(service))

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
