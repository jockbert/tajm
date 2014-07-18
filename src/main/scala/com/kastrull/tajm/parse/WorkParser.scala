package com.kastrull.tajm.parse

import com.kastrull.tajm.ast._

case class WorkParser(
   activityParser: Parser[Activity],
  rangeParser: Parser[TimeRange],
  commentParser: Parser[Comment]) extends Parser[Work] {

  def apply(src: String): Parser.Result[Work] = {
    """[^.]*(\.*).*""".r
    
    
    Left(Work(Activity("/a"),TimeRange(Time(8),Time(10))))
  }
   // ??? // Right(ParseError(src,-1) {})

}