package com.kastrull.tajm

import org.joda.time.LocalDate

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
  val DAY = Day()
  val WEEK = Week()
  
  sealed trait Period;
  case class Day() extends Period {}
  case class Week() extends Period {}
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

case class Time(minutes: Int)

case class TimeRange(from: Time, to: Time)

case class Activity(name: String*) {

  def isParentOf(other: Activity): Boolean = {
    val depth = name.length
    def otherIsLonger = other.name.length > depth
    def equalPrefix = other.name.take(depth) == name

    otherIsLonger && equalPrefix
  }

  def isChildOf(other: Activity) =
    other.isParentOf(this)
}

case class Day(
  date: LocalDate,
  lines: Seq[LineCommand] = Nil,
  comment: Option[String] = None) {}
