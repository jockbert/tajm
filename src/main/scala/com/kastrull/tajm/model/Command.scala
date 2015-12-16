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

object Activity {
  def apply(): Activity = Activity(Nil)
  def apply(p1: String): Activity = Activity(p1 :: Nil)
  def apply(p1: String, p2: String): Activity = Activity(p1 :: p2 :: Nil)
  def apply(p1: String, p2: String, p3: String): Activity = Activity(p1 :: p2 :: p3 :: Nil)
}

case class Activity(name: Seq[String]) {

  def isParentOf(other: Activity): Boolean = {
    val depth = name.length
    def otherIsLonger = other.name.length > depth
    def equalPrefix = other.name.take(depth) == name

    otherIsLonger && equalPrefix
  }

  def isChildOf(other: Activity) =
    other.isParentOf(this)

  override def toString(): String =
    "Activity(" + name.map("'" + _ + "'").mkString(", ") + ")"

}

case class Day(
  date: LocalDate,
  lines: Seq[LineCommand] = Nil,
  comment: Option[String] = None) {}

// FIXME Update behavior
