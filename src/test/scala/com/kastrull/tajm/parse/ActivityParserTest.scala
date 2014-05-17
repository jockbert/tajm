package com.kastrull.tajm.parse

import com.kastrull.tajm.ast._

class ActivityParserTest
  extends ParserTestFixture[Work] {

  val rangeParser = new Parser[TimeRange] {
    def apply(s: String) = s match {
      case e => Right(TestError("" + e, 100))
    }
  }
  val commentParser = new Parser[Comment] {
    def apply(s: String) = s match {
      case e => Right(TestError("" + e, 100))
    }
  }

  def parser = ActivityParser(rangeParser, commentParser)
  
    "ActivityParser" ignore {
  }
}