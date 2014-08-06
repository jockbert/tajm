package com.kastrull.tajm.calculate

import org.joda.time.LocalDate
import com.kastrull.tajm.Activity
import TimeAmmount.DailyAmmounts
import com.kastrull.tajm.LineCommand
import com.kastrull.tajm.Expect
import com.kastrull.tajm.Unexpect

object ExpectationConverter {

  type TimeAmmounts = Map[Activity, DailyAmmounts]
}

case class ExpectationConverter() {
  import ExpectationConverter.TimeAmmounts

  def calculate(): Map[Activity, DailyAmmounts] = {
    Map.empty
  }

  def calculateOneActivity(): DailyAmmounts = {
    Nil
  }

  def calculateLineCommand(cmd: LineCommand): Option[(Activity, Option[Int])] = {
    cmd match {
      case Expect(act, time, Expect.DAY, _)  => Some((act, Some(time.minutes)))
      case Expect(act, time, Expect.WEEK, _) => Some((act, Some(time.minutes / 5)))
      case Unexpect(act, _)                  => Some((act, None))
      case _                                 => None
    }
  }

}
