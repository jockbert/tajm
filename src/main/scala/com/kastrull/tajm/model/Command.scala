package com.kastrull.tajm.model

import org.joda.time.LocalDate
import scala.language.implicitConversions

sealed trait LineCommand {
  def comment: Option[String]
}

case class Work(
  activity: Activity,
  range: TimeRange = TimeRange(Minutes(0), Minutes(0)),
  comment: Option[String] = None)
    extends LineCommand

case class AccumulatedDiffOld(
  activity: Activity,
  diff: Time = Minutes(0),
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

case class Day(
  date: LocalDate,
  lines: Seq[LineCommand] = Nil,
  comment: Option[String] = None) {}

// FIXME Update behavior
