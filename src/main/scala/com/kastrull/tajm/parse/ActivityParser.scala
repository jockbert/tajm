package com.kastrull.tajm.parse

import com.kastrull.tajm.ast._

case class ActivityParser(
  rangeParser: Parser[TimeRange],
  commentParser: Parser[Comment]) extends Parser[Work] {

  def apply(src: String): Parser.Result[Work] = 
    ??? // Right(ParseError(src,-1) {})

}