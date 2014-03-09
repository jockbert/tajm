package com.kastrull.tajm.parse

import org.scalatest._
import com.kastrull.tajm.ast.Comment
import com.kastrull.tajm.ast.Time

class TimePrserTest extends ParserFixture {

  val parser = TimeParser()

  "TimeParser" should "accept simple hours" in {
    "-1" becomes Time(-60)
    "0" becomes Time(0)
    "1" becomes Time(60)
    "2" becomes Time(120)
    "23" becomes Time(1380)
    "24" becomes Time(1440)
    "60" becomes Time(3600)
  }

  it should "accept hours and minutes (h:m)" in {
   // ":" becomes Time(0)
    "0:0" becomes Time(0)
    "000:000" becomes Time(0)
    "010:020" becomes Time(620)
    "10:2" becomes Time(602)
    "1:" becomes Time(60)
    ":1" becomes Time(1)
  }

  /*
  it should "accept negative hours and minutes (h:m)" in { 
    ":" becomes Time(0)
    "0:0" becomes Time(0)
    "000:000" becomes Time(0)
    "010:020" becomes Time(620)
    "10:2" becomes Time(602)
    "1:" becomes Time(60)
    ":1" becomes Time(1)
  }
  */

}