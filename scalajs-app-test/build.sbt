shellPrompt in ThisBuild := { state => Project.extract(state).currentRef.project + "> " }
onLoad in Global := (onLoad in Global).value andThen (Command.process(s"", _))
scalaVersion in ThisBuild := "2.12.3"

lazy val foo = project.in(file("."))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    name := "foo",
    version := "0.1-SNAPSHOT",
    scalaJSUseMainModuleInitializer in Test := true,
    scalaJSModuleKind := ModuleKind.CommonJSModule,
    libraryDependencies ++= Seq(
      //"com.lihaoyi" %%% "utest" % "0.6.0" % "test"
      "org.scalatest" %%% "scalatest" % "3.0.0" % "test"
    )
    //testFrameworks += new TestFramework("utest.runner.Framework")
  )