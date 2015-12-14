package com.kastrull.tajm

object Generators {

  import org.scalacheck._
  import Gen._
  import Arbitrary.arbitrary

  val genPath = Gen.containerOf[Seq, String](Gen.alphaStr.filter { !_.isEmpty() })

  val genActivity = for {
    path <- genPath
  } yield Activity(path)
}
