package com.kastrull.tajm.parse

import com.kastrull.tajm.model.Activity
import Parser._

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
}
