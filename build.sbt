lazy val `lagom-example` =
  project.in(file("."))
    .aggregate(`service-api`)

lazy val `service-api` =
  project.in(file(".") / "modules" / "service-api")

ThisBuild / Test / testOptions += Tests.Argument("-oD")
ThisBuild / IntegrationTest / testOptions += Tests.Argument("-oD")
