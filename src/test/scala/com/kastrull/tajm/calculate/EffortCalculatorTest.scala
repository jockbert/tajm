package com.kastrull.tajm.calculate

import org.scalatest.FreeSpec
import org.scalatest.Matchers
import com.kastrull.tajm.Day
import org.joda.time.LocalDate
import com.kastrull.tajm.Activity
import com.kastrull.tajm.Work
import com.kastrull.tajm.Work
import com.kastrull.tajm.TimeRange
import com.kastrull.tajm.Time

class EffortCalculatorTest
  extends FreeSpec
  with Matchers {

  import EffortCalculator._

  def compareCalculation(work: Seq[Work], expected: EffortMap) = {
    val calculator = EffortCalculator.appy()
    val actual: EffortMap = calculator.calculate(Nil)
    assert(expected === actual)
  }

  "no work" in {

    val work: Seq[Work] = Nil
    val expected: EffortMap = Map.empty

    compareCalculation(work, expected)
  }

  "one work" in {

    val work: Seq[Work] =
      Work(Activity("a"), TimeRange(Time(1 * 60), Time(2 * 60))) ::
        Nil

    val expected: EffortMap = Map.empty

    compareCalculation(work, expected)
  }
}