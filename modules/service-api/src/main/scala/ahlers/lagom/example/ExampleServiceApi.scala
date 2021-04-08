package ahlers.lagom.example

import com.lightbend.lagom.scaladsl.api.Service

/**
 * @author <a href="mailto:michael.ahlers@vectorsolutions.com">Michael Ahlers</a>
 * @since April 08, 2021
 */
trait ExampleServiceApi extends Service:
  override val descriptor = {
   import Service.*
    named("example-service")
      .withAutoAcl(true)
  }
