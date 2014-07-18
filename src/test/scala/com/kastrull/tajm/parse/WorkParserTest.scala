package com.kastrull.tajm.parse

import com.kastrull.tajm.ast._

class WorkParserTest
  extends ParserTestFixture[Work] {
  
  val range = TimeRange(Time(9), Time(10))
  val activity = Activity("a","b","c")

  val activityParser = new Parser[Activity] {
    def apply(s: String) = s match {
      case "activity" => Left(activity)
      case e => Right(TestError(e, 100))
    }
  }

  val rangeParser = new Parser[TimeRange] {
    def apply(s: String) = s match {
      case "range" => Left(range)
      case e => Right(TestError(e, 100))
    }
  }
  
  val commentParser = new Parser[Comment] {
    def apply(s: String) = s match {
      case e => Right(TestError(e, 100))
    }
  }

  def parser = WorkParser(
      activityParser, 
      rangeParser, 
      commentParser)

  "without comment" - {
    
    "simple activity and time" in {
    	"a b" becomes Work(activity, range)
    }
    //FIXME
  }

  "with comment" in {
    //FIXME
  }
  
  "failure" ignore {
    
    
  }
}