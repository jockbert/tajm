package com.kastrull.tajm.parse

import com.kastrull.tajm.ast.Command
import com.kastrull.tajm.ast.Comment
import com.kastrull.tajm.ast.Time

object Parser {

  type Result = Either[Command, ParseError]

  val comment = CommentParser().apply _

  implicit class RichResult(result: Result) {
    def hasCommand: Boolean = result.isLeft
    def hasParseErrors: Boolean = result.isRight
    def command: Command = result.left.get
    def error: ParseError = result.right.get
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

  def parseHourMinute(src: String) = {
    val parts = src.split(':')
    val hours = parts(0);
    val minutes = parts(1);
    val totalMinutes = parseInt(minutes) + parseInt(hours) * 60
    Left(Time(totalMinutes))
  }

  def parseMinutes(src: String) = {
    val rawMinutes = src.split('m')(0)
    Left(Time(parseInt(rawMinutes)))
  }

  def parseHourInt(src: String) = {
    val minutes = parseInt(src) * 60
    Left(Time(minutes))
  }

  def apply(src: String): Parser.Result = {
    val paddedSrc = (' ' + src.toLowerCase() + ' ')
    try
    	doParse(paddedSrc)
    catch {
      case e:Throwable => Right(TimeFormatError(src,0))
    }
  }
  
  private def doParse(paddedSrc: String): scala.util.Left[com.kastrull.tajm.ast.Time,Nothing] = {

    val hasHourMinuteSeparator = paddedSrc.contains(':')
    val hasMinuteSuffix = paddedSrc.contains('m')

    if (hasHourMinuteSeparator) {
      parseHourMinute(paddedSrc)}
    else if (hasMinuteSuffix) 
      parseMinutes(paddedSrc)
    else 
      parseHourInt(paddedSrc)
  }
}


