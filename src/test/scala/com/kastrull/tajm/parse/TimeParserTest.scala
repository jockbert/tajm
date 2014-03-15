package com.kastrull.tajm.parse

import org.scalatest._
import com.kastrull.tajm.ast.Comment
import com.kastrull.tajm.ast.Time

class TimePrserTest extends ParserFixture {

  val parser = TimeParser()

  "TimeParser" - {

    "accept simple hours" ignore {}
    
    "accept simple minutes" ignore {
      "" becomes Time(0)
      "m" becomes Time(0)
      "-1m" becomes Time(-1)
      "0m" becomes Time(0)
      "1 m" becomes Time(1)
      "2m" becomes Time(2)
      "23m" becomes Time(23)
      "24 m" becomes Time(24)
      "60M" becomes Time(60)
      "61 m" becomes Time(61)
    }

    "should accept hours and minutes (h:m)" in {
      ":" becomes Time(0)
      "0:0" becomes Time(0)
      "000:000" becomes Time(0)
      "010:020" becomes Time(620)
      "10:2" becomes Time(602)
      "1:" becomes Time(60)
      ":1" becomes Time(1)
    }

    "should accept float hours (ff.ff or ff,ff)" ignore {}

    "timerange" ignore {}
    "bad times" ignore {}
    "bad time ranges" ignore {}

    
  }

}