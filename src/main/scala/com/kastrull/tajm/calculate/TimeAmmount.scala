package com.kastrull.tajm.calculate

import org.joda.time.LocalDate
import TimeAmmount.DailyAmmounts

object TimeAmmount {

  type DailyAmmount = (LocalDate, Option[Int])
  type DailyAmmounts = Seq[DailyAmmount]

  def apply(dates: DailyAmmount*): TimeAmmount = {

    def sort(ds: Seq[DailyAmmount]) =
      ds.sortWith((a, b) => a._1.compareTo(b._1) == -1)

    def removeRepetition(ds: DailyAmmounts) = {
      val seed: (Option[Int], DailyAmmounts) = (None, Nil)
      val result: (Option[Int], DailyAmmounts) = ds.foldLeft(seed) {
        case ((lastAmmountOpt, result), (date, ammountOpt)) =>

          val newResult =
            if (lastAmmountOpt == ammountOpt) result
            else result :+ ((date, ammountOpt))

          (ammountOpt, newResult)
      }
      result._2
    }

    BasicTimeAmmount(
      removeRepetition(
        sort(dates.toList)))
  }
}

trait TimeAmmount {
  def minutes(date: LocalDate): Option[Int]
  def definedFrom(): Option[LocalDate]
  def definedTo(): Option[LocalDate]
}

case class BasicTimeAmmount(dates: DailyAmmounts)
    extends TimeAmmount {
  def minutes(date: LocalDate): Option[Int] = {
    val upTo = dates.takeWhile(d => date.compareTo(d._1) != -1)
    if (upTo.isEmpty) None
    else upTo.last._2
  }

  def definedFrom(): Option[LocalDate] =
    dates.headOption.map(_._1)

  def definedTo(): Option[LocalDate] =
    if (dates.isEmpty) None
    else if (dates.last._2.isEmpty)
      Some(dates.last._1)
    else None

}
