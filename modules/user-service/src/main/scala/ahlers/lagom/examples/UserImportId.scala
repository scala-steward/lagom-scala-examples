package ahlers.lagom.examples

/**
 * @since May 09, 2021
 * @author <a href="mailto:michael@ahlers.consulting">Michael Ahlers</a>
 */
opaque type UserImportId = Long
object UserImportId:
  def apply(fromLong: Long): UserImportId = fromLong
