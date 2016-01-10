package com.kastrull.tajm.parse

import scala.Left
import scala.Right

import org.scalacheck.Gen
import org.scalacheck.Prop.AnyOperators
import org.scalacheck.Prop.forAll
import org.scalacheck.Prop.forAllNoShrink
import org.scalacheck.Prop.propBoolean
import org.scalacheck.Properties

import com.kastrull.tajm.model.EmptyLine
import com.kastrull.tajm.model.Line

class LineReaderProps extends Properties("LineReaderProps") {

  val GOOD_LINE = "good line"
  val BAD_LINE = "bad line"
  val PARSE_ERROR = "Some!#!"

  val parser: Parser = new Parser() {
    def parseActivity(s: String) = ???
    def parseHours(s: String) = ???
    def parseMinutes(s: String) = ???
    def parseClock(s: String) = ???
    def parseTime(s: String) = ???
    def parseExpectedTime(s: String) = ???
    def parseTimeRange(s: String) = ???
    def parseComment(s: String) = ???
    def parseDiff(s: String) = ???
    def parseUnexpectTime(s: String) = ???
    def parseBrake(s: String) = ???
    def parseDate(s: String) = ???
    def parseActivityLine(s: String) = ???
    def parseBrakeLine(s: String) = ???
    def parseLine(s: String): ParserResult[Line] =
      if (s == GOOD_LINE) Left(Line(EmptyLine, None))
      else Right(PARSE_ERROR)
  }

  val lineReader = LineReader(parser)

  val goodLineGen = Gen.const(GOOD_LINE)
  val badLineGen = Gen.const(BAD_LINE)

  val goodLinesGen = Gen.listOf(goodLineGen)

  val badLinesGen =
    goodLinesGen.map(x => (x ::: BAD_LINE :: x, x.size))

  property("read good lines to model") = forAll(goodLinesGen) {
    lines: Seq[String] =>

      val result = lineReader.read(lines.iterator)

      result.isLeft && (result.left.get.size ?= lines.size)
  }

  property("Catch parse error for a bad line") = forAllNoShrink(badLinesGen) {
    case (lines: Seq[String], errorLineNumber: Int) =>
      val result = lineReader.read(lines.iterator)

      val expectedMessage = PARSE_ERROR
      // TODO change error to something more suitable, including line number
      //val expectedMessage = s"Parse error at line $errorLineNumber: $PARSE_ERROR"

      result.right.get ?= expectedMessage
  }
}
