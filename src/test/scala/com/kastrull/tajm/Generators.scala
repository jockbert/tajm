package com.kastrull.tajm

object Generators {

  import org.scalacheck._
  import Gen._
  import Arbitrary.arbitrary

  val genActivity = for {
    path <- arbitrary[Seq[String]]
  } yield Activity(path)

}

// FIXME Empty object
