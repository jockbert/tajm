package com.kastrull.tajm.output

import com.kastrull.tajm.model.Activity

trait OutputFormatter {

  def formatActivity(x: Activity): String

}
