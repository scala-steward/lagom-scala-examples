import com.lightbend.lagom.core.LagomVersion

// TODO: Restore once more libraries are compiled for RC3.
// scalaVersion := "3.0.0-RC3"
scalaVersion := "3.0.0-RC2"

libraryDependencies ++=
  ("com.typesafe.play" %% "play-json" % "2.10.0-RC2" ::
    Nil)
    .map(_
      .cross(CrossVersion.for3Use2_13))

libraryDependencies ++=
  "com.devskiller.friendly-id" % "friendly-id" % "1.1.0" ::
    Nil
