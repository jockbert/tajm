package com.kastrull.tajm.parse

import Parser._
import com.kastrull.tajm.model._

object Parser {
  type ParseResult[VAL] = Either[VAL, String]
}

trait Parser {

  def parseActivity(s: String): ParseResult[Activity]
}

case object NormalFormParser extends Parser {

  private val parser = NormalFormRegexParser

  def translate[X](result: parser.ParseResult[X]): ParseResult[X] = {
    result match {
      case parser.Success(x, _)       => Left(x)
      case parser.Error(message, _)   => Right(message)
      case parser.Failure(message, _) => Right(message)
    }
  }

  def parseActivity(s: String): ParseResult[Activity] =
    translate(parser.parseAll(parser.activity, s))

  def parseHours(s: String): ParseResult[Hours] =
    translate(parser.parseAll(parser.hours, s))

  def parseMinutes(s: String): ParseResult[Minutes] =
    translate(parser.parseAll(parser.minutes, s))

  def parseClock(s: String): ParseResult[Clock] =
    translate(parser.parseAll(parser.clock, s))

  def parseTime(s: String): ParseResult[Time] =
    translate(parser.parseAll(parser.time, s))
}
