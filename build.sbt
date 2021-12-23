scalaVersion := "2.13.7"
scalaJSUseMainModuleInitializer := true

enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)

resolvers += Resolver.githubPackages("uosis")

libraryDependencies ++= Seq(
      "org.scala-js" %%% "scalajs-dom" % "1.1.0",
      "com.raquo" %%% "laminar" % "0.13.0",
      "com.github.uosis" %%% "laminar-web-components-material" % "0.1.0",
)

/*
Compile / npmDependencies ++= Seq(
      "chart.js" -> "3.6.2"
)
*/