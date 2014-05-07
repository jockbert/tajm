package com.kastrull.tajm.parse

import com.kastrull.tajm.ast.Time


case class TimeParser() extends Parser[Time] {

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

  def apply(src: String): Parser.Result[Time] = {
    val paddedSrc = (' ' + src.toLowerCase() + ' ')
    try
      doParse(paddedSrc)
    catch {
      case e: Throwable => Right(TimeFormatError(src, 0))
    }
  }

  private def doParse(paddedSrc: String): scala.util.Left[com.kastrull.tajm.ast.Time, Nothing] = {

    val hasHourMinuteSeparator = paddedSrc.contains(':')
    val hasMinuteSuffix = paddedSrc.contains('m')

    if (hasHourMinuteSeparator) {
      parseHourMinute(paddedSrc)
    } else if (hasMinuteSuffix)
      parseMinutes(paddedSrc)
    else
      parseHourInt(paddedSrc)
  }
}
