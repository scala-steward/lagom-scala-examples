import com.lightbend.lagom.core.LagomVersion
import sbt.librarymanagement.syntax.ExclusionRule

// TODO: Restore once more libraries are compiled for RC3.
// scalaVersion := "3.0.0-RC3"
scalaVersion := "3.0.0-RC2"

libraryDependencies ++=
  "org.scala-lang.modules" %% "scala-xml" % "2.0.0-RC1" ::
    Nil

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
  "com.h2database" % "h2" % "1.4.200" ::
    "org.postgresql" % "postgresql" % "42.2.19" ::
    "org.flywaydb" % "flyway-core" % "7.5.3" ::
    Nil

libraryDependencies ++=
  ("com.github.tminglei" %% "slick-pg" % "0.19.5" ::
    Nil)
    .map(_
      .cross(CrossVersion.for3Use2_13))

libraryDependencies ++=
  ("com.typesafe.akka" %% "akka-actor-testkit-typed" % LagomVersion.akka % Test ::
    "com.typesafe.akka" %% "akka-persistence-testkit" % LagomVersion.akka % Test ::
    "com.lightbend.lagom" %% "lagom-scaladsl-testkit" % LagomVersion.current % Test ::
    Nil)
    .map(_
      .cross(CrossVersion.for3Use2_13))

libraryDependencies +=
  "org.jooq" % "joor" % "0.9.13" % Test

/** Fixes an incompatibility. */
dependencyOverrides ++=
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.11.4" ::
    Nil

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
