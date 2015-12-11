package com.kastrull.tajm.parse

import com.kastrull.tajm.Activity

object Parser {

  type ParseResult[X] = scala.util.Try[X]

}

trait Parser {
  import Parser._

  def activity(s: String): ParseResult[Activity]

}

case class NormalFormParserX() extends {

}
