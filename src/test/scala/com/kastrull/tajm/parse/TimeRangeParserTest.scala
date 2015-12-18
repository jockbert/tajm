package com.kastrull.tajm.parse

import com.kastrull.tajm.model.TimeRange
import com.kastrull.tajm.model._

class TimeRangeParserTest extends ParserTestFixture[TimeRange] {

  def parser = LegacyParser.timeRange

  "proper timerange" in {
    "0 .. 10:04" becomes TimeRange(Hours(0), Clock(10, 4))
    "..10:04 " becomes TimeRange(Hours(0), Clock(10, 4))
    " 10:04 .. 10:04 " becomes TimeRange(Clock(10, 4), Clock(10, 4))
    " 10:04 .. 10 " becomes TimeRange(Clock(10, 4), Hours(600))
    ".." becomes TimeRange(Hours(0), Hours(0))
    "10 m .. 10" becomes TimeRange(Minutes(10), Hours(600))
  }

  "bad time ranges returns error" in {
    "a" fails ()
    "0..a" fails ()
    " 0 .. a " fails ()
    "0..0..0" fails ()
  }
}

// FIXME Update behavior and change from test to property
