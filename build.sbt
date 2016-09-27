name := "r-scala-dsl"

organization := "com.github.ldaniels528"

version := "0.1"

scalaVersion := "2.11.8"

scalacOptions ++= Seq("-deprecation", "-encoding", "UTF-8", "-feature", "-target:jvm-1.8", "-unchecked",
  "-Ywarn-adapted-args", "-Ywarn-value-discard", "-Xlint")

javacOptions ++= Seq("-Xlint:deprecation", "-Xlint:unchecked", "-source", "1.8", "-target", "1.8", "-g:vars")

// General Dependencies
libraryDependencies ++= Seq(
  // Testing Dependencies
  "org.mockito" % "mockito-all" % "1.9.5" % "test",
  "org.scalatest" %% "scalatest" % "2.2.2" % "test",
  //
  // Logging Dependencies
  "log4j" % "log4j" % "1.2.17" % "test",
  "org.slf4j" % "slf4j-api" % "1.7.7",
  "org.slf4j" % "slf4j-log4j12" % "1.7.7" % "test"
)