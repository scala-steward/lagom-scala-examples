name := "lagom-example"
description := "Demonstrates Lagom's key features and serves as a guide for similar projects."

ThisBuild / homepage := None
ThisBuild / startYear := Some(2021)

ThisBuild / developers ++=
  Developer("michaelahlers", "Michael Ahlers", "michael@ahlers.consulting", url("https://github.com/michaelahlers")) ::
    Nil

ThisBuild / scmInfo :=
  Some(
    ScmInfo(
      browseUrl = url("https://github.com/michaelahlers/lagom-example"),
      connection = "https://github.com/michaelahlers/lagom-example.git",
      devConnection = Some("git@github.com:michaelahlers/lagom-example.git")
    )
  )

ThisBuild / licenses := Nil
