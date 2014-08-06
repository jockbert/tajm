package com.kastrull.tajm.calculate

import org.scalatest.FreeSpec
import org.scalatest.Matchers

import com.kastrull.tajm.Activity
import com.kastrull.tajm.TimeImplicits.hoursToTime
import com.kastrull.tajm.TimeRange
import com.kastrull.tajm.Work

import EffortCalculator.EffortMap

class EffortCalculatorTest
    extends FreeSpec
    with Matchers {

  trait WithExpected {
    def expects(expected: EffortMap): Unit
  }

  val aa = Activity("aa")
  val aag = Activity("aa", "g")
  val aagh = Activity("aa", "g", "h")
  val bb = Activity("bb")

  def givenWork(works: Work*) = new WithExpected {
    def expects(expected: EffortMap) = {
      val calculator = EffortCalculator()
      val actual: EffortMap = calculator.calculate(works.toSeq)
      assert(expected === actual)
    }
  }

  "no work" in {
    givenWork().
      expects(Map.empty)
  }

  "one work" in {
    givenWork(
      Work(aa, TimeRange(1, 2))
    ).expects(Map(aa -> 60))
  }

  "multiple work - one activity" in {
    givenWork(
      Work(aa, TimeRange(1, 2)),
      Work(aa, TimeRange(3, 4))
    ).expects(Map(aa -> 120))
  }

  "multiple work - multiple activities" in {
    givenWork(
      Work(aa, TimeRange(1, 2)),
      Work(bb, TimeRange(2, 3)),
      Work(aa, TimeRange(3, 4))
    ).expects(Map(aa -> 120, bb -> 60))
  }

  "nested activites" in {
    givenWork(
      Work(aa, TimeRange(1, 2)),
      Work(aag, TimeRange(2, 4)),
      Work(aagh, TimeRange(4, 7))
    ).expects(Map(aa -> 60, aag -> 120, aagh -> 180))
  }
}

