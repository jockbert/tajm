package com.kastrull.tajm.parse

import com.kastrull.tajm.ast.Command
import com.kastrull.tajm.ast.Comment
import com.kastrull.tajm.ast.Time

object Parser {

  type ParseErrors = Seq[ParseError]
  type Result = Either[Command, ParseErrors]

  val comment = CommentParser().apply _

  implicit class RichResult(result: Result) {
    def hasCommand: Boolean = result.isLeft
    def hasParseErrors: Boolean = result.isRight
    def command: Command = result.left.get
    def errors: ParseErrors = result.right.get
  }
}

sealed trait Parser {
  import Parser._

  def apply(src: String): Result
}

case class CommentParser() extends Parser {
  def apply(src: String): Parser.Result =
    Left(Comment(src.trim()))
}
case class TimeParser() extends Parser {
  def apply(src: String): Parser.Result = {

    val hasHourMinuteSeparator = src.contains(':')
    if (hasHourMinuteSeparator) {
      val (hour, minutes) = src.partition(_ == ':')
      Left(Time(
          Integer.parseInt(minutes.tail) + 
          Integer.parseInt(hour) * 60))
      
    } else {
      val minutes = Integer.parseInt(src) * 60
      Left(Time(minutes))
    }
  }
}

