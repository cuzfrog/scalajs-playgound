import Settings._
import org.scalajs.sbtplugin.cross.CrossProject

shellPrompt in ThisBuild := { state => Project.extract(state).currentRef.project + "> " }
onLoad in Global := (onLoad in Global).value andThen (Command.process(s"", _))


lazy val root = (project in file("."))
  .settings(commonSettings)
  .settings(
    name := "scalajs-playground",
    version := "0.0.1",
    libraryDependencies ++= Seq(
      "org.scala-js" %%% "scalajs-java-time" % "0.2.1",
      "org.scala-js" %% "scalajs-test-interface" % scalaJSVersion,
      "com.lihaoyi" %%% "utest" % "0.6.0" % "test"
    ),
    scalaJSUseMainModuleInitializer := true,
    scalaJSUseMainModuleInitializer in Test := true,
    scalaJSModuleKind := ModuleKind.CommonJSModule,
    testFrameworks += new TestFramework("utest.runner.Framework")
  ).enablePlugins(ScalaJSPlugin)

lazy val testSuite = CrossProject(
  jvmId = "testSuiteJVM",
  jsId = "testSuiteJS",
  base = file("testSuite"),
  crossType = CrossType.Full
).
  jsConfigure(_ .enablePlugins(ScalaJSPlugin)).
  settings(commonSettings).
  settings(
    libraryDependencies ++= Seq(
      "com.lihaoyi" %%% "utest" % "0.6.0" % "test"
    ),
    testFrameworks += new TestFramework("utest.runner.Framework")
  ).
  jsSettings(
    name := "testSuite on JS"
  ).
  jsConfigure(_.dependsOn(root)).
  jvmSettings(
    name := "testSuite on JVM"
  )

lazy val testSuiteJS = testSuite.js
lazy val testSuiteJVM = testSuite.jvm

addCommandAlias("testAll",";testSuiteJS/test;testSuiteJVM/test")

val `js-tests` = project.dependsOn(root)
  .enablePlugins(ScalaJSPlugin)
  .settings(commonSettings)
  .settings(
    scalaJSUseMainModuleInitializer := true,
    scalaJSModuleKind := ModuleKind.CommonJSModule,
    testFrameworks += new TestFramework("com.github.cuzfrog.mytest.MyFramework"),
    libraryDependencies ++= Seq(
      "org.scala-js" %%% "scalajs-dom" % "0.9.3"
    )
  )