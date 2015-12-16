package com.kastrull.tajm.output

import com.kastrull.tajm.model.Activity

case object NormalFormFormatter
    extends OutputFormatter {

  def format(x: Activity): String =
    x.path.mkString("/", "/", "")
}
