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

  def parseInt(s: String) = {
    val trimmed = s.trim()
    if (trimmed.isEmpty()) 0 
    else Integer.parseInt(trimmed)
  }

  def apply(src: String): Parser.Result = {
    val paddedSrc = (' ' + src + ' ')
	    
    val hasHourMinuteSeparator = src.contains(':')
    if (hasHourMinuteSeparator) {
      val parts = paddedSrc.split(':')
      val hours = parts(0);
      val minutes = parts(1);
      val totalMinutes = parseInt(minutes) + parseInt(hours) * 60
      Left(Time(totalMinutes))

    } else {
      val minutes = parseInt(paddedSrc)
      Left(Time(minutes))
    }
  }
}

