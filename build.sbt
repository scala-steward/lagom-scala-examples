lazy val `lagom-example` = project.in(file("."))

ThisBuild / Test / testOptions += Tests.Argument("-oD")
ThisBuild / IntegrationTest / testOptions += Tests.Argument("-oD")
