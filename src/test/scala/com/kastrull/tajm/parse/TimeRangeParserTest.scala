package com.kastrull.tajm.parse

import com.kastrull.tajm.ast.TimeRange
import com.kastrull.tajm.ast.Time

class TimeRangeParserTest extends ParserTestFixture[TimeRange] {

  val mockTimeParser = new Parser[Time] {
    def apply(s: String) = s.trim() match {
      case "" => Left(Time(0))
      case "0" => Left(Time(0))
      case "10" => Left(Time(600))
      case "10 m" => Left(Time(10))
      case "10:04" => Left(Time(604))
      case e => Right(TestError("" + e, 10))
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

    "bad time ranges returns error" in {
      "a" becomes MissingTimeRangeError("a", 0)
      "0..a" becomes TestError("0..a",13)
      " 0 .. a " becomes TestError(" 0 .. a ", 15)
      "0..0..0" becomes TestError("0..0..0", 13)
    }
  }

}