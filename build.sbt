lazy val `lagom-example` =
  project.in(file("."))
    .aggregate(
      `akka-persistence-support`,
      `user-service`,
      `user-service-api` /*,
      `user-service-api-models`*/ )

lazy val `akka-persistence-support` =
  project.in(file(".") / "modules" / "akka-persistence-support")

lazy val `user-service-api` =
  project.in(file(".") / "modules" / "user-service-api")
/*.dependsOn(`user-service-api-models`)*/

/**
 * @todo Restore this once Scala 2.13.6 releases with support for TASTy format 28.0.3.
 * @see [[https://contributors.scala-lang.org/t/scala-2-13-6-planning/4975 Scala 2.13.6 planning]]
 */
/*lazy val `user-service-api-models` =
  project.in(file(".") / "modules" / "user-service-api-models")*/

lazy val `user-service` =
  project.in(file(".") / "modules" / "user-service")
    .dependsOn(
      `user-service-api`)

ThisBuild / Test / testOptions += Tests.Argument("-oD")
ThisBuild / IntegrationTest / testOptions += Tests.Argument("-oD")
