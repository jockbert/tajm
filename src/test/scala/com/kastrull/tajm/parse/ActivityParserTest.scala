package com.kastrull.tajm.parse

import com.kastrull.tajm.Activity

class ActivityParserTest
    extends ParserTestFixture[Activity] {

  def parser = LegacyParser.activity

  "root element" in {
    "" becomes Activity()
    "    " becomes Activity()
    "/" becomes Activity()
    " / " becomes Activity()
  }

  "one level element" in {
    "/a" becomes Activity("a")
    "/v/" becomes Activity("v")
  }

  "multiple level element" in {
    "/a/b/c" becomes Activity("a", "b", "c")
    " /v/W/ " becomes Activity("v", "W")
  }

  "failures" in {
    "a/b" fails ()
  }
}

// FIXME Update behavior and change from test to property
