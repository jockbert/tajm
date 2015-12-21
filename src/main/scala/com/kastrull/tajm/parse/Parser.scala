package com.kastrull.tajm.parse

import Parser._
import com.kastrull.tajm.model._

object Parser {
  type ParserResult[VAL] = Either[VAL, String]
}

trait Parser {

  def parseActivity(s: String): ParserResult[Activity]
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
}
