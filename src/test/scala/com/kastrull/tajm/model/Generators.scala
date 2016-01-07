package com.kastrull.tajm.model

object Generators {

  import org.scalacheck._
  import Gen._
  import Arbitrary.arbitrary

  val genPath = Gen.containerOf[Seq, String](Gen.alphaStr.filter { !_.isEmpty() })

  val genActivity = for {
    path <- genPath
  } yield Activity(path)

  val genHours = for {
    h <- Gen.choose(0, 24)
  } yield Hours(h)

  val genMinutes = for {
    m <- Gen.choose(0, 1440)
  } yield Minutes(m)

  val genClock = for {
    h <- Gen.choose(0, 24)
    m <- Gen.choose(0, 59)
  } yield Clock(h, m)

  val genTime: Gen[Time] = Gen.oneOf(genHours, genMinutes, genClock)

  val genTimeRange: Gen[TimeRange] =
    for {
      from <- genTime
      to <- genTime
    } yield TimeRange(from, to)

  val genExpectedTime = for {
    time <- genTime
    todayOnly <- Gen.oneOf(true, false)
  } yield ExpectedTime(time, todayOnly)

  val genUnexpectTime = Gen.const(UnexpectTime)

  val genComment = Gen.containerOf[Seq, String](Gen.alphaStr).map {
    words => Comment(words.mkString(" "))
  }

  val genDiff = genTime.map(AccumulatedDiff(_))

  val genBrake = Gen.oneOf(true, false).map(Brake(_))

}
