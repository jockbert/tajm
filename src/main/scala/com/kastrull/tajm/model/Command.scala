package com.kastrull.tajm.model

import org.joda.time.LocalDate
import scala.language.implicitConversions

sealed trait LineCommand {
  def comment: Option[String]
}

case class Work(
  activity: Activity,
  range: TimeRange = TimeRange(Time(0), Time(0)),
  comment: Option[String] = None)
    extends LineCommand

case class AccumulatedDiff(
  activity: Activity,
  diff: Time = Time(0),
  comment: Option[String] = None)
    extends LineCommand

object Expect {
  val DAY = Day
  val WEEK = Week

  sealed trait Period;
  case object Day extends Period
  case object Week extends Period
}

case class Expect(
  activity: Activity,
  expected: Time,
  period: Expect.Period,
  comment: Option[String] = None)
    extends LineCommand

case class Unexpect(
  activity: Activity,
  comment: Option[String] = None)
    extends LineCommand

case class Note(
  comment: Option[String] = None)
    extends LineCommand

object TimeImplicits {
  import scala.language.implicitConversions

  implicit def hoursToTime(hours: Int) =
    Time(hours * 60)
  implicit def hourAndMinuteTupleToTime(hm: (Int, Int)) =
    Time(hm._1 * 60 + hm._2)
}

case class Time(minutes: Int)

case class TimeRange(from: Time, to: Time)

case class Day(
  date: LocalDate,
  lines: Seq[LineCommand] = Nil,
  comment: Option[String] = None) {}

// FIXME Update behavior
