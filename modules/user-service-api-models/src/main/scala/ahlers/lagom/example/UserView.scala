package ahlers.lagom.example

import play.api.libs.json.*
import play.api.libs.functional.syntax.*

/**
 * @author <a href="mailto:michael.ahlers@vectorsolutions.com">Michael Ahlers</a>
 * @since April 22, 2021
 */
case class UserView(
  userId: String,
  name: String)

object UserView:

  /** @see [[https://scalacenter.github.io/scala-3-migration-guide/docs/incompatibilities/other-changed-features.html#explicit-call-to-unapply Explicit Call to `unapply`]] */
  def tuple(user:UserView): (String,String) =
    import user.userId
    import user.name
    (userId, name)

given Format[UserView] =
  (__ \ "userId").format[String].and(
    (__ \ "name").format[String])
    .apply(UserView.apply, UserView.tuple)
