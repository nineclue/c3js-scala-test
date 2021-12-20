scalaVersion := "2.13.7"
scalaJSUseMainModuleInitializer := true

enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)

libraryDependencies ++= Seq(
      "org.scala-js" %%% "scalajs-dom" % "2.0.0",
)

/*
Compile / npmDependencies ++= Seq(
      "chart.js" -> "3.6.2"
)
*/