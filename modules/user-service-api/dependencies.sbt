import com.lightbend.lagom.core.LagomVersion
import sbt.librarymanagement.syntax.ExclusionRule

val excludeScalaXml =
  ExclusionRule()
    .withOrganization("org.scala-lang.modules")
    .withName("scala-xml")
    .withCrossVersion(CrossVersion.for3Use2_13)

libraryDependencies ++=
  ("com.lightbend.lagom" %% "lagom-scaladsl-api" % LagomVersion.current ::
    Nil).map(_
    .cross(CrossVersion.for3Use2_13)
    .excludeAll(excludeScalaXml))

libraryDependencies ++=
  "com.devskiller.friendly-id" % "friendly-id" % "1.1.0" ::
    Nil

val libraryDependenciesAkk =
  "com.typesafe.akka" %% "akka-slf4j" % LagomVersion.akka ::
    Nil

libraryDependencies ++=
  ("com.typesafe.akka" %% "akka-slf4j" % LagomVersion.akka).cross(CrossVersion.for3Use2_13) ::
    Nil
