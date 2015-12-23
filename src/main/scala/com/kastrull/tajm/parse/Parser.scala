package com.kastrull.tajm.parse

import Parser._
import com.kastrull.tajm.model._

object Parser {
  type ParserResult[VAL] = Either[VAL, String]
}

trait Parser {
  def parseActivity(s: String): ParserResult[Activity]
  def parseHours(s: String): ParserResult[Hours]
  def parseMinutes(s: String): ParserResult[Minutes]
  def parseClock(s: String): ParserResult[Clock]
  def parseTime(s: String): ParserResult[Time]
  def parseExpected(s: String): ParserResult[ExpectedTime]
  def parseTimeRange(s: String): ParserResult[TimeRange]
}

case object NormalFormParser extends Parser {

  private val parser = NormalFormRegexParser

  def translate[X](result: parser.ParseResult[X]): ParserResult[X] = {
    result match {
      case parser.Success(x, _)       => Left(x)
      case parser.Error(message, _)   => Right(message)
      case parser.Failure(message, _) => Right(message)
    }
  }

  def doParse[X](parserX: parser.Parser[X], s: String): ParserResult[X] =
    translate(parser.parseAll(parserX, s))

  def parseActivity(s: String): ParserResult[Activity] =
    doParse(parser.activity, s)

  def parseHours(s: String): ParserResult[Hours] =
    doParse(parser.hours, s)

  def parseMinutes(s: String): ParserResult[Minutes] =
    doParse(parser.minutes, s)

  def parseClock(s: String): ParserResult[Clock] =
    doParse(parser.clock, s)

  def parseTime(s: String): ParserResult[Time] =
    doParse(parser.time, s)

  def parseExpected(s: String): ParserResult[ExpectedTime] =
    doParse(parser.expected, s)

  def parseTimeRange(s: String): ParserResult[TimeRange] =
    doParse(parser.timeRange, s)
}
