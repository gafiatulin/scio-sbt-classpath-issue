name := "com.spotify.scio-sbt-classpath-issue"

version := "0.1"

scalaVersion := "2.12.13"

crossScalaVersions := Seq("2.12.12", "2.12.13")

lazy val root = project
  .in(file("."))
  .configs(IntegrationTest)
  .settings(
    classLoaderLayeringStrategy := ClassLoaderLayeringStrategy.Flat,
    IntegrationTest / classLoaderLayeringStrategy := ClassLoaderLayeringStrategy.Flat,
    Defaults.itSettings,
    libraryDependencies ++= Seq(
      "com.spotify" %% "scio-core" % "0.10.2" % "it",
      "org.apache.beam" % "beam-runners-google-cloud-dataflow-java" % "2.28.0" % "it",
      "ch.qos.logback" % "logback-classic" % "1.2.3" % "it",
      "org.scalatest" %% "scalatest" % "3.2.8" % "it"
    )
  )
