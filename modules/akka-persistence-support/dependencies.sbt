import com.lightbend.lagom.core.LagomVersion

// TODO: Restore once more libraries are compiled for RC3.
// scalaVersion := "3.0.0-RC3"
scalaVersion := "3.0.0-RC2"

libraryDependencies ++=
  ("com.typesafe.akka" %% "akka-persistence-typed" % "2.6.14" ::
    Nil)
    .map(_
      .cross(CrossVersion.for3Use2_13))
