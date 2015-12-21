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
}
