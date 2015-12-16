package com.kastrull.tajm.output

import com.kastrull.tajm.model.Activity

trait OutputFormatter {

  def format(x: Activity): String

}
