package com.kastrull.tajm.output

import com.kastrull.tajm.Activity

trait OutputFormatter {

  def format(x: Activity): String

}
