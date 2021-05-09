package ahlers.lagom

import java.util.UUID
import io.estatico.newtype.macros.newtype

/**
 * @author <a href="mailto:michael.ahlers@vectorsolutions.com">Michael Ahlers</a>
 * @since April 23, 2021
 */
package object examples {

  @newtype case class UserId(toUuid: UUID)
  object UserId

  @newtype case class UserImportId(toLong: Long)
  object UserImportId

}
