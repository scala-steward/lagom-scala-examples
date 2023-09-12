import com.lightbend.lagom.core.LagomVersion

resolvers += "scala-integration".at("https://scala-ci.typesafe.com/artifactory/scala-integration/")

/** This module remains compiled against Scala 2.13 since Lagom makes extensive use of macros for declaring service descriptors. */
scalaVersion := "2.13.12"

libraryDependencies += "com.typesafe.akka" %% "akka-slf4j" % LagomVersion.akka

libraryDependencies ++=
  "com.lightbend.lagom" %% "lagom-scaladsl-api" % LagomVersion.current ::
    "com.lightbend.lagom" %% "lagom-scaladsl-client" % LagomVersion.current ::
    Nil

libraryDependencies ++=
  "com.devskiller.friendly-id" % "friendly-id" % "1.1.0" ::
    "io.estatico" %% "newtype" % "0.4.4" ::
    Nil
