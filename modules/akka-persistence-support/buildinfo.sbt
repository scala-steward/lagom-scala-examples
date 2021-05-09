import com.typesafe.sbt.SbtGit.GitKeys.{ gitBranch, gitHeadCommit, gitHeadCommitDate }

enablePlugins(BuildInfoPlugin)

buildInfoObject := "AkkaPersistenceSupport"
buildInfoPackage := "akka.persistence.typed.scaladsl.support"

buildInfoKeys ++=
  Seq[BuildInfoKey](
    name,
    version,
    scalaVersion,
    sbtVersion,
    gitBranch,
    gitHeadCommit,
    gitHeadCommitDate)
