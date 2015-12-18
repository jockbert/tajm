package com.kastrull.tajm.parse

import org.scalatest._
import com.kastrull.tajm.model._

class TimeParserTest extends ParserTestFixture[Time] {

  val parser = LegacyParser.time

  "simple hours" in {
    "" becomes Hours(0)
    " " becomes Hours(0)
    "-1" becomes Hours(-1)
    "0" becomes Hours(0)
    " 1 " becomes Hours(1)
    "2" becomes Hours(2)
    "30" becomes Hours(30)
  }

  "simple minutes" in {
    "" becomes Hours(0)
    "m" becomes Minutes(0)
    "-1m" becomes Minutes(-1)
    "0m" becomes Minutes(0)
    "1 m" becomes Minutes(1)
    "2m" becomes Minutes(2)
    "23M" becomes Minutes(23)
    "24 min" becomes Minutes(24)
    "60MinUtEs" becomes Minutes(60)
    "61 m" becomes Minutes(61)
  }

  "hours and minutes (h:m)" in {
    ":" becomes Clock(0, 0)
    "0:0" becomes Clock(0, 0)
    "000:000" becomes Clock(0, 0)
    "010:020" becomes Clock(10, 20)
    "10:2" becomes Clock(10, 2)
    "1:" becomes Clock(1, 0)
    ":1" becomes Clock(0, 1)
  }

  "just bad values" in {
    "a1" fails ()
    "1.2.3" fails ()
  }
}

// FIXME Update behavior and change from test to property
