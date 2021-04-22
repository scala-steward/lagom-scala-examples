lazy val `lagom-example` =
  project.in(file("."))
    .aggregate(
      `user-service`,
      `user-service-api`)

lazy val `user-service-api` =
  project.in(file(".") / "modules" / "user-service-api")

lazy val `user-service` =
  project.in(file(".") / "modules" / "user-service")
    .dependsOn(`user-service-api`)

ThisBuild / Test / testOptions += Tests.Argument("-oD")
ThisBuild / IntegrationTest / testOptions += Tests.Argument("-oD")
