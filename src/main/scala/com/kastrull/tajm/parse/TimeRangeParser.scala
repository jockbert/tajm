package com.kastrull.tajm.parse

import com.kastrull.tajm.ast.Time
import com.kastrull.tajm.ast.TimeRange

case class TimeRangeParser(timeParser: Parser[Time]) extends Parser[TimeRange] {
  def apply(src: String): Parser.Result[TimeRange] = {

    val isRange = src.contains("..")

    if (isRange) {
      val position = src.indexOf("..")
      val halfs = (" " + src + " ").splitAt(position + 1)
      
      val firstResult = timeParser(halfs._1)
      val first = firstResult.left.get

      val secondResult = timeParser(halfs._2.tail.tail)
      val second = secondResult.left.get

      Left(TimeRange(first, second))
    } else Left(TimeRange(Time(-1), Time(-1)))
  }
}