package com.kastrull.tajm.ast

trait Command {

}


case class Comment(text: String) extends Command

case class Time(minute: Int) extends Command

case class TimeRange(from: Time, to:Time) extends Command


