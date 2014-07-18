package com.kastrull.tajm.ast

import org.joda.time.LocalDate

trait LineCommand {
	def comment: Option[String]
}

case class Comment(text: String) 

case class Time(minutes: Int) 

case class TimeRange(from: Time, to:Time)

case class Work(
    activity: Activity,
    range: TimeRange = TimeRange(Time(0),Time(0)),
    comment: Option[String] = None) 
    extends LineCommand

case class Activity(name: String*) {

  def isParentOf(other: Activity): Boolean = {
    val depth = name.length
    def otherIsLonger = other.name.length > depth
    def equalPrefix = other.name.take(depth) == name

    otherIsLonger && equalPrefix
  }
  
  def isChildOf(other: Activity) =
    other.isParentOf(this)
}



case class WorkDay(
    date: LocalDate, 
    lines: Seq[LineCommand]) {}