package com.kastrull.tajm.parse

import com.kastrull.tajm.ast.TimeRange
import com.kastrull.tajm.ast.Time

class TimeRangeParserTest extends ParserFixture[TimeRange] {

  val mockTimeParser = new Parser[Time] {
    def apply(s: String) = s.trim() match {
      case "" => Left(Time(0))
      case "0" => Left(Time(0))
      case "10" => Left(Time(600))
      case "10 m" => Left(Time(10))
      case "10:04" => Left(Time(604))
      case e => throw new Exception("Unknown value '%s'".format(e))
      //case e => Right(TimeFormatError("Unknown value '%s'".format(e), 0))
    }
  }

  def parser = TimeRangeParser(mockTimeParser)

  "TimeParser" - {

    "proper timerange" in {
      "0 .. 10:04" becomes TimeRange(Time(0), Time(604))
      "..10:04 " becomes TimeRange(Time(0), Time(604))
      " 10:04 .. 10:04 " becomes TimeRange(Time(604), Time(604))
      " 10:04 .. 10 " becomes TimeRange(Time(604), Time(600))
      ".." becomes TimeRange(Time(0), Time(0))
      "10 m .. 10" becomes TimeRange(Time(10), Time(600))
    }

    "Let plain time pass through" ignore {
      "10:04" becomes Time(604)
      " 10 m" becomes Time(10)
    }

    "bad time ranges returns error" ignore {
      // "a" becomes
      // "0..a" becomes
      // "0..0..0" becomes
    }
  }

}