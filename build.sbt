ThisBuild / tlBaseVersion := "0.0" // your current series x.y

ThisBuild / organization := "io.chrisdavenport"
ThisBuild / organizationName := "Christopher Davenport"
ThisBuild / licenses := Seq(License.MIT)
ThisBuild / developers := List(
  tlGitHubDev("christopherdavenport", "Christopher Davenport")
)
ThisBuild / tlCiReleaseBranches := Seq("main")
ThisBuild / tlSonatypeUseLegacyHost := true


val Scala213 = "2.13.7"

ThisBuild / crossScalaVersions := Seq(Scala213, "3.1.1")
ThisBuild / scalaVersion := Scala213

ThisBuild / testFrameworks += new TestFramework("munit.Framework")

val http4sV = "0.23.18"
val munitCatsEffectV = "1.0.7"


// Projects
lazy val `publicsuffix` = tlCrossRootProject
  .aggregate(core, retrieval, `retrieval-client`, examples)

lazy val core = crossProject(JVMPlatform, JSPlatform)
  .crossType(CrossType.Full)
  .in(file("core"))
  .settings(
    name := "publicsuffix",

    libraryDependencies ++= Seq(
      "org.typelevel" %%% "munit-cats-effect-3"  % munitCatsEffectV % Test,
    )
  ).jsSettings(
    scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.CommonJSModule)},
  )

lazy val retrieval = crossProject(JVMPlatform, JSPlatform)
  .crossType(CrossType.Pure)
  .in(file("retrieval"))
  .dependsOn(core)
  .settings(
    name := "publicsuffix-retrieval",
    libraryDependencies ++= Seq(
      "org.http4s" %%% "http4s-client" % http4sV
    )
  )

lazy val `retrieval-client` = crossProject(JVMPlatform, JSPlatform)
  .crossType(CrossType.Pure)
  .in(file("retrieval-client"))
  .dependsOn(retrieval)
  .settings(
    name := "publicsuffix-retrieval-client",
    libraryDependencies ++= Seq(
      "org.http4s" %%% "http4s-ember-client" % http4sV
    )
  )

lazy val site = project.in(file("site"))
  .enablePlugins(TypelevelSitePlugin)
  .dependsOn(core.jvm)

lazy val examples = crossProject(JVMPlatform, JSPlatform)
  .crossType(CrossType.Full)
  .in(file("examples"))
  .enablePlugins(NoPublishPlugin)
  .jsSettings(
    scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.CommonJSModule)},
    scalaJSUseMainModuleInitializer := true,
    mainClass := Some("GenericRetrieval")
  )
  .dependsOn(core, `retrieval-client`)