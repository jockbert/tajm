package com.kastrull.tajm.parse

import com.kastrull.tajm.model._
import org.joda.time.LocalDate

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

  def parseExpectedTime(s: String): ParserResult[ExpectedTime] =
    doParse(parser.expectedTime, s)

  def parseTimeRange(s: String): ParserResult[TimeRange] =
    doParse(parser.timeRange, s)

  def parseComment(s: String): ParserResult[Comment] =
    doParse(parser.comment, s)

  def parseDiff(s: String): ParserResult[AccumulatedDiff] =
    doParse(parser.accumulatedDiff, s)

  def parseUnexpectTime(s: String): ParserResult[UnexpectTime.type] =
    doParse(parser.unexpectTime, s)

  def parseBrake(s: String): ParserResult[Brake] =
    doParse(parser.brake, s)

  def parseDate(s: String): ParserResult[LocalDate] =
    doParse(parser.date, s)

  def parseActivityLine(s: String): ParserResult[ActivityLine] =
    doParse(parser.activityLine, s)

  def parseBrakeLine(s: String): ParserResult[BrakeLine] =
    doParse(parser.brakeLine, s)

  def parseLine(s: String): ParserResult[Line] =
    doParse(parser.line, s)
}
