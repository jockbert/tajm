name := "Hello tajm"

version := "0.1"

scalaVersion := "2.11.7"

scalacOptions += "-feature"

scalacOptions += "-deprecation"

libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.1" % "test"

libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.12.5" % "test"

libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.2"

//see: https://github.com/nscala-time/nscala-time
libraryDependencies += "com.github.nscala-time" %% "nscala-time" % "1.2.0"

unmanagedSourceDirectories in Compile := (scalaSource in Compile).value :: Nil

unmanagedSourceDirectories in Test := (scalaSource in Test).value :: Nil


org.scalastyle.sbt.ScalastylePlugin.Settings

// wartremoverWarnings ++= Warts.unsafe
