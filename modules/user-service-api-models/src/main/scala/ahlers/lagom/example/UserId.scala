package ahlers.lagom.example

import java.util.UUID

/**
 * @author <a href="mailto:michael.ahlers@vectorsolutions.com">Michael Ahlers</a>
 * @since April 22, 2021
 */
type UserId = UUID
object UserId:
  def apply(value: UUID):UserId = value
