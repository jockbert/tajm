package com.kastrull.tajm.output

import com.kastrull.tajm.model._

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

}
