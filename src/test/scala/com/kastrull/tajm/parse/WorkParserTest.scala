package com.kastrull.tajm.parse

import com.kastrull.tajm.Work
import com.kastrull.tajm.Activity
import com.kastrull.tajm.TimeRange
import scala.language.postfixOps

class WorkParserTest
  extends ParserTestFixture[Work] {

  def parser = Parser.work

  "without comment" in {
    "work /a 9..10" becomes
      Work(
        Activity("a"),
        TimeRange(9 h, 10 h))
  }

  "with comment" in {
    " work /a/b/c/9..10 \" hello world \"" becomes
      Work(
        Activity("a", "b", "c"),
        TimeRange(9 h, 10 h),
        Some("hello world"))
  }

  "failure" in {
    " /a 1..2 " fails ()
    "work " fails ()
  }
}