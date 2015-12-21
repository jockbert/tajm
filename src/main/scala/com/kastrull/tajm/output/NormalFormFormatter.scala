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
}
