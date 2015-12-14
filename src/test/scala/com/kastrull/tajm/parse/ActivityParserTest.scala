package com.kastrull.tajm.parse

import com.kastrull.tajm.Activity

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

import com.kastrull.tajm.Generators.genActivity

import org.scalacheck.Prop.AnyOperators
import org.scalacheck.Prop.forAll
import org.scalacheck.Properties
import com.kastrull.tajm.output.NormalFormFormatter

class ActivityParserProps extends Properties("ActivityParser") {

  val formatter = NormalFormFormatter
  val parser = NormalFormParser.activity _

  property("formatter-parser-roundtrip") = forAll(genActivity) { a: Activity =>
    val x = formatParseRoundtrip(a)
    x ?= a
  }

  def formatParseRoundtrip(a: Activity) = {
    val text = formatter.format(a)

    parser(text) match {
      case Left(a)        => a
      case Right(message) => throw new Exception("Parse error " + message)
    }
  }
}

// FIXME Update behavior and change from test to property
