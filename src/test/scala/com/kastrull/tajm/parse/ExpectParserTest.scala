package com.kastrull.tajm.parse

import com.kastrull.tajm.Expect
import com.kastrull.tajm.Activity
import scala.language.postfixOps

class ExpectParserTest extends ParserTestFixture[Expect] {

  def parser = Parser.expect

  "with day" in {
    "expect /a day 4" becomes
      Expect(Activity("a"), 4 h, Expect.DAY)
  }
  "with week" in {
    "expect /a week 4" becomes
      Expect(Activity("a"), 4 h, Expect.WEEK)
  }

  "with root activity and default time" in {
    "expect week" becomes
      Expect(Activity(), 0 h, Expect.WEEK)
  }

  "with comment" in {
    "expect /a week 4 \"hello\"" becomes
      Expect(
        Activity("a"), 4 h,
        Expect.WEEK, Some("hello"))
  }
}

// FIXME Update behavior and change from test to property
