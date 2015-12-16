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

class ActivityParserTest
    extends ParserTestFixture[Activity] {

  def parser = LegacyParser.activity

  "root element" in {
    "" becomes Activity()
    "    " becomes Activity()
    "/" becomes Activity()
    " / " becomes Activity()
  }

  "one level element" in {
    "/a" becomes Activity("a")
    "/v/" becomes Activity("v")
  }

  "multiple level element" in {
    "/a/b/c" becomes Activity("a", "b", "c")
    " /v/W/ " becomes Activity("v", "W")
  }

  "failures" in {
    "a/b" fails ()
  }
}

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

// FIXME Update behavior and change from test to property
