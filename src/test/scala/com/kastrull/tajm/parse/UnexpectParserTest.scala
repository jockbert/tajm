package com.kastrull.tajm.parse

import com.kastrull.tajm.model.Unexpect
import com.kastrull.tajm.model.Activity
import scala.language.postfixOps

class UnexpectParserTest extends ParserTestFixture[Unexpect] {

  def parser = LegacyParser.unexpect

  "with day" in {
    "unexpect /a" becomes
      Unexpect(Activity("a"))
  }
  "with week" in {
    "unexpect /a" becomes
      Unexpect(Activity("a"))
  }

  "with root activity and default time" in {
    "unexpect" becomes
      Unexpect(Activity())
  }

  "with comment" in {
    "unexpect /a \"hello\"" becomes
      Unexpect(Activity("a"), Some("hello"))
    "unexpect  \"hello\"" becomes
      Unexpect(Activity(), Some("hello"))
  }
}

// FIXME Update behavior and change from test to property
