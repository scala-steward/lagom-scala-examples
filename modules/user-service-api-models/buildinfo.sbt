import com.typesafe.sbt.SbtGit.GitKeys.{ gitBranch, gitHeadCommit, gitHeadCommitDate }

enablePlugins(BuildInfoPlugin)

buildInfoObject := "UserServiceApiModelsBuildInfo"
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
