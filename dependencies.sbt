ThisBuild / scalaVersion := "3.0.0-RC2"

libraryDependencies ++=
  ("org.scalacheck" %% "scalacheck" % "1.15.3" % Test) ::
    ("org.scalatest" %% "scalatest" % "3.2.7" % Test) ::
    ("org.scalatestplus" %% "scalacheck-1-15" % "3.2.7.0" % Test) ::
    Nil
