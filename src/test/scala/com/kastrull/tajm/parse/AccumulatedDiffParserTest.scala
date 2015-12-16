package com.kastrull.tajm.parse

import com.kastrull.tajm.model.AccumulatedDiff
import com.kastrull.tajm.model.Activity
import scala.language.postfixOps

class AccumulatedDiffParserTest
    extends ParserTestFixture[AccumulatedDiff] {

  def parser = LegacyParser.accDiff

  "set with specific value" in {
    "accdiff /v 1:30" becomes
      AccumulatedDiff(Activity("v"), 1 h 30)
    "accdiff 1:30" becomes
      AccumulatedDiff(Activity(), 1 h 30)
  }

  "clear" in {
    "accdiff /v" becomes
      AccumulatedDiff(Activity("v"))
    "accdiff" becomes
      AccumulatedDiff(Activity())
  }

  "with comment" in {
    "accdiff \"clear root\"" becomes
      AccumulatedDiff(Activity(), 0 h, Some("clear root"))
  }
}

// FIXME Update behavior and change from test to property
