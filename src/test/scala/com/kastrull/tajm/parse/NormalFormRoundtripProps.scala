package com.kastrull.tajm.parse

import org.scalacheck.Gen
import org.scalacheck.Prop.AnyOperators
import org.scalacheck.Prop.forAll
import org.scalacheck.Properties

import com.kastrull.tajm.model.Activity
import com.kastrull.tajm.model.Generators.genActivity
import com.kastrull.tajm.output.NormalFormFormatter.formatActivity
import com.kastrull.tajm.parse.Parser.ParseResult

import NormalFormParser.parseActivity

class NormalFormRoundtripProps extends Properties("NormalFormRoundtrip") {

  property("activity") =
    roundtrip(genActivity, formatActivity, parseActivity)

  def roundtrip[X](
    generator: Gen[X],
    formatter: X => String,
    parser: String => ParseResult[X]) =

    forAll(generator) { originalX: X =>
      val textRepresentation = formatter(originalX)
      val parsedX = getX(parser(textRepresentation))
      parsedX ?= originalX
    }

  def getX[X](result: ParseResult[X]): X = {
    result match {
      case Left(a)        => a
      case Right(message) => throw new Exception("Parse error " + message)
    }
  }
}
