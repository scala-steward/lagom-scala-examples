import com.lightbend.lagom.core.LagomVersion
import sbt.librarymanagement.syntax.ExclusionRule

// TODO: Restore once more libraries are compiled for RC3.
// scalaVersion := "3.0.0-RC3"
scalaVersion := "3.0.0-RC2"

libraryDependencies ++=
  "org.scala-lang.modules" %% "scala-xml" % "2.0.0-RC1" ::
    Nil

libraryDependencies ++=
  ("com.lightbend.lagom" %% "lagom-scaladsl-api" % LagomVersion.current ::
    "com.lightbend.lagom" %% "lagom-scaladsl-persistence" % LagomVersion.current ::
    Nil)
    .map(_
      .cross(CrossVersion.for3Use2_13))

excludeDependencies +=
  ExclusionRule()
    .withOrganization("org.scala-lang.modules")
    .withName("scala-xml")
    .withCrossVersion(CrossVersion.for3Use2_13)
