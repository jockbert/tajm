package com.kastrull.tajm.calculate

import org.scalatest.FreeSpec
import org.scalatest.Matchers
import com.kastrull.tajm.Activity
import org.joda.time.LocalDate
import TimeAmmount.DailyAmmounts
import TimeAmmount.DailyAmmount
import com.kastrull.tajm.Note
import com.kastrull.tajm.LineCommand
import com.kastrull.tajm.Work
import com.kastrull.tajm.TimeImplicits._
import com.kastrull.tajm.TimeRange
import com.kastrull.tajm.AccumulatedDiff
import com.kastrull.tajm.Expect
import com.kastrull.tajm.Unexpect
import com.kastrull.tajm.Day
import com.kastrull.tajm.Day

import com.kastrull.tajm.TimeImplicits._

class ExpectedWorkConverterTest
    extends FreeSpec
    with Matchers {

  val march1 = new LocalDate(2014, 3, 1)
  val march5 = new LocalDate(2014, 3, 5)
  val activityA = Activity("a")
  val activityB = Activity("b")

  trait Converter {
    val calculator = ExpectationConverter
  }

  trait DaysToExpectedTimeAmmountsTest extends Converter {
    def expects(expected: Map[Activity, DailyAmmounts])
  }

  def daysToExpectedTimeAmmounts(days: Seq[Day]) =
    new DaysToExpectedTimeAmmountsTest {
      def expects(expected: Map[Activity, DailyAmmounts]) = {
        val actual = calculator.daysToExpectedTimeAmmounts(days)
        assert(expected === actual)
      }
    }

  "days to expected time ammounts" - {

    "no expectations" in
      daysToExpectedTimeAmmounts {
        Nil
      }.expects(Map.empty)

    "one activity expectation under one day" in {
      val day = Day(march1, Expect(activityA, 4, Expect.DAY) :: Nil)

      val ammountsA = (march1, Some(4 * 60)) :: Nil

      daysToExpectedTimeAmmounts {
        day :: Nil
      }.expects {
        Map(activityA -> ammountsA)
      }
    }

    "multiple expectations under multiple days" in {

      val day1 = Day(march1,
        Expect(activityA, 4, Expect.DAY) ::
          Expect(activityB, 1, Expect.DAY) :: Nil)

      val day5 = Day(march5, Unexpect(activityB) :: Nil)

      val ammountsA =
        (march1, Some(4 * 60)) :: Nil

      val ammountsB =
        (march1, Some(1 * 60)) ::
          (march5, None) :: Nil

      daysToExpectedTimeAmmounts {
        day1 :: day5 :: Nil
      }.expects {
        Map(
          activityA -> ammountsA,
          activityB -> ammountsB)
      }
    }
  }

  trait LineCommandTest extends Converter {
    def expects(expected: Option[(Activity, Option[Int])])
  }

  def translateLineCommand(cmd: LineCommand) =
    new LineCommandTest {
      def expects(expected: Option[(Activity, Option[Int])]) = {
        val actual = calculator.translateLineCommand(cmd)
        assert(expected === actual)
      }
    }

  "translate line command" - {

    "Note" in
      translateLineCommand {
        Note(Some("Hello"))
      }.expects(None)

    "Work" in
      translateLineCommand {
        Work(activityA, TimeRange(1, 2))
      }.expects(None)

    "AccumulatedDiff" in
      translateLineCommand {
        AccumulatedDiff(activityA, 1)
      }.expects(None)

    "Expect day" in
      translateLineCommand {
        Expect(activityA, 6, Expect.DAY)
      }.expects {
        Some((activityA, Some(60 * 6)))
      }

    "Expect week" in
      translateLineCommand {
        Expect(activityA, 40, Expect.WEEK)
      }.expects {
        Some((activityA, Some(60 * 8)))
      }

    "Unexpect" in
      translateLineCommand {
        Unexpect(activityA)
      }.expects {
        Some((activityA, None))
      }
  }
}

// FIXME Update behavior and change from test to property
