import com.lightbend.lagom.core.LagomVersion
import sbt.librarymanagement.syntax.ExclusionRule

libraryDependencies ++=
  ("com.typesafe.akka" %% "akka-slf4j" % LagomVersion.akka ::
    Nil)
    .map(_
      .cross(CrossVersion.for3Use2_13))

libraryDependencies ++=
  ("com.lightbend.lagom" %% "lagom-logback" % LagomVersion.current ::
    "com.lightbend.lagom" %% "lagom-reloadable-server" % LagomVersion.current ::
    "com.lightbend.lagom" %% "lagom-scaladsl-akka-discovery-service-locator" % LagomVersion.current ::
    "com.lightbend.lagom" %% "lagom-scaladsl-dev-mode" % LagomVersion.current ::
    "com.lightbend.lagom" %% "lagom-scaladsl-kafka-broker" % LagomVersion.current ::
    "com.lightbend.lagom" %% "lagom-scaladsl-kafka-client" % LagomVersion.current ::
    "com.lightbend.lagom" %% "lagom-scaladsl-persistence-jdbc" % LagomVersion.current ::
    "com.lightbend.lagom" %% "lagom-scaladsl-server" % LagomVersion.current ::
    Nil)
    .map(_
      .cross(CrossVersion.for3Use2_13))

libraryDependencies ++=
  ("com.typesafe.play" %% "play-akka-http-server" % LagomVersion.play ::
    Nil)
    .map(_
      .cross(CrossVersion.for3Use2_13))

libraryDependencies ++=
  //"org.flywaydb" % "flyway-core" % "7.5.3" ::
  //"com.github.tminglei" %% "slick-pg" % "0.19.5" ::
  //"com.github.tminglei" %% "slick-pg_play-json" % "0.19.5" ::
  Nil

libraryDependencies ++=
  ("com.typesafe.akka" %% "akka-actor-testkit-typed" % LagomVersion.akka % Test ::
    "com.typesafe.akka" %% "akka-persistence-testkit" % LagomVersion.akka % Test ::
    "com.lightbend.lagom" %% "lagom-scaladsl-testkit" % LagomVersion.current % Test ::
    Nil)
    .map(_
      .cross(CrossVersion.for3Use2_13))

libraryDependencies +=
  "org.jooq" % "joor" % "0.9.13" % Test

excludeDependencies ++=
  ("lagom-logback" ::
    "lagom-reloadable-server" ::
    "lagom-scaladsl-dev-mode" ::
    "lagom-scaladsl-server" ::
    Nil)
    .map(
      ExclusionRule()
        .withOrganization("com.lightbend.lagom")
        .withName(_)
        .withCrossVersion(CrossVersion.binary))

excludeDependencies ++=
  ("play-akka-http-server" ::
    Nil)
    .map(
      ExclusionRule()
        .withOrganization("com.typesafe.play")
        .withName(_)
        .withCrossVersion(CrossVersion.binary))

excludeDependencies +=
  ExclusionRule()
    .withOrganization("org.scala-lang.modules")
    .withName("scala-xml")
    .withCrossVersion(CrossVersion.for3Use2_13)
