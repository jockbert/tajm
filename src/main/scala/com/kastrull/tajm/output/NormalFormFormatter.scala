package com.kastrull.tajm.output

import com.kastrull.tajm.model.Activity

case object NormalFormFormatter
    extends OutputFormatter {

  def formatActivity(x: Activity): String =
    x.path.mkString("/", "/", "")
}
