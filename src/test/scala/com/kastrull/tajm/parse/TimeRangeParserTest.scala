package com.kastrull.tajm.parse

import com.kastrull.tajm.TimeRange
import com.kastrull.tajm.Time

class TimeRangeParserTest extends ParserTestFixture[TimeRange] {

  def parser = Parser.timeRange

  "proper timerange" in {
    "0 .. 10:04" becomes TimeRange(Time(0), Time(604))
    "..10:04 " becomes TimeRange(Time(0), Time(604))
    " 10:04 .. 10:04 " becomes TimeRange(Time(604), Time(604))
    " 10:04 .. 10 " becomes TimeRange(Time(604), Time(600))
    ".." becomes TimeRange(Time(0), Time(0))
    "10 m .. 10" becomes TimeRange(Time(10), Time(600))
  }

  "bad time ranges returns error" in {
    "a" fails ()
    "0..a" fails ()
    " 0 .. a " fails ()
    "0..0..0" fails ()
  }
}
