import com.lightbend.lagom.core.LagomVersion
import sbt.librarymanagement.syntax.ExclusionRule

libraryDependencies ++=
  ("com.typesafe.akka" %% "akka-slf4j" % LagomVersion.akka ::
    Nil)
    .map(_
      .cross(CrossVersion.for3Use2_13))

libraryDependencies ++=
  ("com.lightbend.lagom" %% "lagom-scaladsl-api" % LagomVersion.current ::
    Nil).map(_
    .cross(CrossVersion.for3Use2_13))

libraryDependencies +=
  "org.scala-lang.modules" %% "scala-xml" % "2.0.0-RC1"

libraryDependencies ++=
  "com.devskiller.friendly-id" % "friendly-id" % "1.1.0" ::
    Nil

excludeDependencies +=
  ExclusionRule()
    .withOrganization("org.scala-lang.modules")
    .withName("scala-xml")
    .withCrossVersion(CrossVersion.for3Use2_13)
