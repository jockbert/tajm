name := "Hello tajm"

version := "0.1"

scalaVersion := "2.10.3"

scalacOptions += "-feature"

scalacOptions += "-deprecation"

libraryDependencies += "org.scalatest" % "scalatest_2.10" % "2.0" % "test"

libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.11.3" % "test"

//see: https://github.com/nscala-time/nscala-time
libraryDependencies += "com.github.nscala-time" %% "nscala-time" % "1.2.0"

unmanagedSourceDirectories in Compile := (scalaSource in Compile).value :: Nil

unmanagedSourceDirectories in Test := (scalaSource in Test).value :: Nil


org.scalastyle.sbt.ScalastylePlugin.Settings
