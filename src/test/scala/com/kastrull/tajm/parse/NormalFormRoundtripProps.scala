package com.kastrull.tajm.parse

import org.scalacheck.Gen
import org.scalacheck.Prop.AnyOperators
import org.scalacheck.Prop.forAll
import org.scalacheck.Properties

import com.kastrull.tajm.model._
import com.kastrull.tajm.model.Generators._
import com.kastrull.tajm.output.NormalFormFormatter._
import com.kastrull.tajm.parse.Parser.ParserResult

class NormalFormRoundtripProps extends Properties("NormalFormRoundtrip") {

  val parser: Parser = NormalFormParser
  import parser._

  property("activity") =
    roundtrip(genActivity)(formatActivity, parseActivity)

  property("hours") =
    roundtrip(genHours)(formatHours, parseHours)

  property("minutes") =
    roundtrip(genMinutes)(formatMinutes, parseMinutes)

  property("clock") =
    roundtrip(genClock)(formatClock, parseClock)

  property("time") =
    roundtrip(genTime)(formatTime, parseTime)

  property("timerange") =
    roundtrip(genTimeRange)(formatTimeRange, parseTimeRange)

  property("expect") =
    roundtrip(genExpected)(formatExpected, parseExpected)

  private def roundtrip[X](
    generator: Gen[X])(
      formatter: X => String,
      parser: String => ParserResult[X]) =

    forAll(generator) { originalX: X =>
      val textRepresentation = formatter(originalX)
      val parsedX = getX(parser(textRepresentation))
      parsedX ?= originalX
    }

  private def getX[X](result: ParserResult[X]): X = {
    result match {
      case Left(a)        => a
      case Right(message) => throw new Exception("Parse error " + message)
    }
  }
}
