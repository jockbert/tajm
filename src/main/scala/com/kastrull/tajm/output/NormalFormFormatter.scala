package com.kastrull.tajm.output

import com.kastrull.tajm.model._
import org.joda.time.LocalDate

case object NormalFormFormatter
    extends OutputFormatter {

  def formatActivity(x: Activity): String =
    x.path.mkString("/", "/", "")

  def formatHours(x: Hours): String =
    x.hours.toString()

  def formatMinutes(x: Minutes): String =
    x.minutes + "m"

  def formatClock(x: Clock): String =
    x.h + ":" + x.m

  def formatTime(x: Time): String =
    x match {
      case h: Hours   => formatHours(h)
      case m: Minutes => formatMinutes(m)
      case c: Clock   => formatClock(c)
    }

  def formatExpectedTime(x: ExpectedTime): String =
    x match {
      case ExpectedTime(time, false) => "expect " + formatTime(time)
      case ExpectedTime(time, true)  => "expect " + formatTime(time) + " once"
    }

  def formatTimeRange(x: TimeRange): String =
    formatTime(x.from) + "-" + formatTime(x.to)

  def formatComment(x: Comment): String =
    "# " + x.text

  def formatDiff(x: AccumulatedDiff): String =
    "diff " + formatTime(x.diff)

  def formatUnexpectTime(x: UnexpectTime.type): String =
    "unexpect"

  def formatBrake(x: Brake): String =
    if (x.isLunch) "lunch" else "brake"

  def formatParameter(x: Parameter): String =
    x match {
      case diff: AccumulatedDiff => formatDiff(diff)
      case et: ExpectedTime      => formatExpectedTime(et)
      case ut: UnexpectTime.type => formatUnexpectTime(ut)
    }

  def formatDate(x: LocalDate): String =
    x.getYear() + "-" + x.getMonthOfYear() + "-" + x.getDayOfMonth()

  def formatActivityLine(x: ActivityLine): String =
    formatActivity(x.activity) + " " +
      x.duration.map(formatTimeRange).getOrElse("") + " " +
      x.parameters.map(formatParameter).mkString(" ")

  def formatBrakeLine(x: BrakeLine): String =
    formatBrake(x.brake) + " " + formatTimeRange(x.duration)

  def formatContent(x: Content): String =
    x match {
      case line: ActivityLine => formatActivityLine(line)
      case line: BrakeLine    => formatBrakeLine(line)
      case line: DateLine     => formatDate(line.date)
      case EmptyLine          => ""
    }

  def formatLine(x: Line): String =
    formatContent(x.content) + " " + x.comment.map(formatComment).getOrElse("")

  def formatLines(x: Seq[Line]): String =
    x.map(formatLine).mkString("\n")
}
