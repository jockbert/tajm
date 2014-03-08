package com.kastrull.tajm.ast

sealed trait Command {

}

case class Comment(text: String) extends Command

case class Time(minute: Int) extends Command


