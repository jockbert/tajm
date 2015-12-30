package com.kastrull.tajm.parse

import com.kastrull.tajm.model.AccumulatedDiffOld
import com.kastrull.tajm.model.Activity
import scala.language.postfixOps

class AccumulatedDiffParserTest
    extends ParserTestFixture[AccumulatedDiffOld] {

  def parser = LegacyParser.accDiff

  "set with specific value" in {
    "accdiff /v 1:30" becomes
      AccumulatedDiffOld(Activity("v"), 1 h 30)
    "accdiff 1:30" becomes
      AccumulatedDiffOld(Activity(), 1 h 30)
  }

  "clear" in {
    "accdiff /v" becomes
      AccumulatedDiffOld(Activity("v"))
    "accdiff" becomes
      AccumulatedDiffOld(Activity())
  }

  "with comment" in {
    "accdiff \"clear root\"" becomes
      AccumulatedDiffOld(Activity(), 0 h, Some("clear root"))
  }
}

// FIXME Update behavior and change from test to property
