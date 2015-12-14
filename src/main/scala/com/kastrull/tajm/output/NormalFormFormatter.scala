package com.kastrull.tajm.output

import com.kastrull.tajm.Activity

case object NormalFormFormatter
    extends OutputFormatter {

  def format(x: Activity): String =
    x.name.mkString("/", "/", "")
}
