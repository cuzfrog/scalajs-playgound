import sbt.Keys._
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
      "com.lihaoyi" %%% "utest" % "0.4.7" % "test"
    ),
    scalaJSUseMainModuleInitializer := true,
    scalaJSModuleKind := ModuleKind.CommonJSModule,
    testFrameworks += new TestFramework("utest.runner.Framework"),
    parallelExecution in Test := false
  ).enablePlugins(ScalaJSPlugin)

lazy val testSuite = CrossProject(
  jvmId = "testSuiteJVM",
  jsId = "testSuiteJS",
  base = file("testSuite"),
  crossType = CrossType.Full
).
  jsConfigure(_ .enablePlugins(ScalaJSPlugin)).
  settings(commonSettings: _*).
  settings(
    libraryDependencies ++= Seq(
      "com.lihaoyi" %%% "utest" % "0.4.7" % "test"
    ),
    testFrameworks += new TestFramework("utest.runner.Framework"),
    parallelExecution in Test := false
  ).
  jsSettings(
    name := "java.time testSuite on JS"
  ).
  jsConfigure(_.dependsOn(root)).
  jvmSettings(
    name := "java.time testSuite on JVM"
  )

lazy val testSuiteJS = testSuite.js
lazy val testSuiteJVM = testSuite.jvm

addCommandAlias("testAll",";testSuiteJS/test;testSuiteJVM/test")