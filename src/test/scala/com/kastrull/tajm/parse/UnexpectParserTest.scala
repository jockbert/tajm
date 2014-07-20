package com.kastrull.tajm.parse

import com.kastrull.tajm.Unexpect
import com.kastrull.tajm.Activity
import scala.language.postfixOps

class UnexpectParserTest extends ParserTestFixture[Unexpect] {

  def parser = Parser.unexpect

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