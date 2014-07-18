package com.kastrull.tajm.parse

import com.kastrull.tajm.ast._
import com.kastrull.tajm.parse.Parser._

class ActivityParserTest
  extends ParserTestFixture[Activity] {

  def parser = ActivityParser()

  def successfulParse(input: String, expected: Activity) = {
    val result = parser(input)
    assert(result.hasCommand)
    assert(result.command === expected)
  }

  "ActivityParser" - {

    "root element" ignore { // FIXME solve
      successfulParse("", Activity())
      successfulParse("    ", Activity())
      successfulParse("/", Activity())
      successfulParse(" / ", Activity())
    }

    "one level element" - {
      successfulParse("/a", Activity("a"))
      successfulParse("/v/", Activity("v"))
    }
    
    "multiple level element" - {
      successfulParse("/a/b/c", Activity("a","b","c"))
      successfulParse(" /v/W/ ", Activity("v","W"))
    }

    "failures" ignore {
      //FIXME failures
    }

  }
}