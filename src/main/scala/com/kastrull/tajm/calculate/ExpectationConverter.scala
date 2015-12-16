package com.kastrull.tajm.calculate

import org.joda.time.LocalDate
import com.kastrull.tajm.model.Activity
import TimeAmmount.DailyAmmount
import TimeAmmount.DailyAmmounts
import com.kastrull.tajm.model.LineCommand
import com.kastrull.tajm.model.Expect
import com.kastrull.tajm.model.Unexpect
import com.kastrull.tajm.model.Day

case object ExpectationConverter {

  type ActivityAmmounts = Map[Activity, DailyAmmounts]

  def daysToExpectedTimeAmmounts(days: Seq[Day]): Map[Activity, DailyAmmounts] = {

    def removeActivityFromTuples(
      grp: (Activity, Seq[(Activity, DailyAmmount)])): (Activity, DailyAmmounts) =
      (grp._1, grp._2.map(_._2))

    val tuples: Seq[(Activity, DailyAmmount)] =
      for {
        day <- days
        lineCommand <- day.lines
        (activity, value) <- translateLineCommand(lineCommand)
      } yield (activity, (day.date, value))

    tuples.groupBy(_._1).map(removeActivityFromTuples)

  }

  def translateLineCommand(cmd: LineCommand): Option[(Activity, Option[Int])] = {
    cmd match {
      case Expect(act, time, Expect.DAY, _)  => Some((act, Some(time.minutes)))
      case Expect(act, time, Expect.WEEK, _) => Some((act, Some(time.minutes / 5)))
      case Unexpect(act, _)                  => Some((act, None))
      case _                                 => None
    }
  }
}
