package com.kastrull.tajm.output

import com.kastrull.tajm.model.Activity
import com.kastrull.tajm.model.Hours

case object NormalFormFormatter
    extends OutputFormatter {

  def formatActivity(x: Activity): String =
    x.path.mkString("/", "/", "")

  def formatHours(x: Hours): String =
    x.hours.toString()
}
