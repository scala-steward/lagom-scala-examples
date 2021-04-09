import com.typesafe.sbt.SbtGit.GitKeys.{ gitBranch, gitHeadCommit, gitHeadCommitDate }

enablePlugins(BuildInfoPlugin)

buildInfoObject := "UserServiceApiBuildInfo"
buildInfoPackage := "ahlers.lagom.example"

buildInfoKeys ++=
  Seq[BuildInfoKey](
    name,
    version,
    scalaVersion,
    sbtVersion,
    gitBranch,
    gitHeadCommit,
    gitHeadCommitDate)
