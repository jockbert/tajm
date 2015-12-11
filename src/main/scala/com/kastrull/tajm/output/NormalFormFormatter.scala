package com.kastrull.tajm.output

import com.kastrull.tajm.Activity

case class NormalFormFormatter()
    extends OutputFormatter {

  def format(x: Activity): String =
    x.name.mkString("/", "/", "")
}
