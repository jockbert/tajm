package com.kastrull.tajm.model

trait Time {
  def minutes: Int
}

case class Minutes(minutes: Int) extends Time

case class Hours(hours: Int) extends Time {
  def minutes = hours * 60
}

case class Clock(h: Int, m: Int) extends Time {
  def minutes = h * 60 + m
}

object TimeImplicits {
  import scala.language.implicitConversions

  implicit def hoursToTime(hours: Int) =
    Hours(hours)
  implicit def hourAndMinuteTupleToTime(hm: (Int, Int)) =
    Clock(hm._1, hm._2)
}
