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
      val secondResult = timeParser(halfs._2.tail.tail)

      if (firstResult.isRight) {
        val first = firstResult.right.get
        Right(first.create(src, first.position))

      } else if (secondResult.isRight) {
        val second = secondResult.right.get
        Right(second.create(src, 
            halfs._1.size + 1 + second.position))

      } else {
        val first = firstResult.left.get
        val second = secondResult.left.get
        Left(TimeRange(first, second))

      }
    } else Right(MissingTimeRangeError("a", 0))
  }
}