package com.kastrull.tajm.parse

import org.scalacheck.Gen
import org.scalacheck.Prop.AnyOperators
import org.scalacheck.Prop.forAll
import org.scalacheck.Properties

import com.kastrull.tajm.model.Activity
import com.kastrull.tajm.model.Generators.genActivity
import com.kastrull.tajm.output.NormalFormFormatter.format
import com.kastrull.tajm.parse.Parser.ParseResult

import NormalFormParser.activity

class ActivityParserProps extends Properties("ActivityParser") {

  property("formatter-parser-roundtrip") =
    roundtrip(activity, format, genActivity)

  def roundtrip[X](
    parser: String => ParseResult[X],
    formatter: X => String,
    generator: Gen[X]) =

    forAll(generator) { originalX: X =>
      val textRepresentation = formatter(originalX)
      val parsedX = stripResult(parser(textRepresentation))
      parsedX ?= originalX
    }

  def stripResult[X](result: ParseResult[X]): X = {
    result match {
      case Left(a)        => a
      case Right(message) => throw new Exception("Parse error " + message)
    }
  }
}
