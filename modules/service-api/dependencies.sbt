import com.lightbend.lagom.core.LagomVersion

libraryDependencies ++=
  ("com.lightbend.lagom" %% "lagom-scaladsl-api" % LagomVersion.current).cross(CrossVersion.for3Use2_13) ::
    Nil

libraryDependencies ++=
  ("com.typesafe.akka" %% "akka-slf4j" % LagomVersion.akka).cross(CrossVersion.for3Use2_13) ::
    Nil
