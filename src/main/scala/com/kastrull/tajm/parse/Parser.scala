package com.kastrull.tajm.parse

import com.kastrull.tajm.model._
import org.joda.time.LocalDate

trait Parser {
  def parseActivity(s: String): ParserResult[Activity]
  def parseHours(s: String): ParserResult[Hours]
  def parseMinutes(s: String): ParserResult[Minutes]
  def parseClock(s: String): ParserResult[Clock]
  def parseTime(s: String): ParserResult[Time]
  def parseExpectedTime(s: String): ParserResult[ExpectedTime]
  def parseTimeRange(s: String): ParserResult[TimeRange]
  def parseComment(s: String): ParserResult[Comment]
  def parseDiff(s: String): ParserResult[AccumulatedDiff]
  def parseUnexpectTime(s: String): ParserResult[UnexpectTime.type]
  def parseBrake(s: String): ParserResult[Brake]
  def parseDate(s: String): ParserResult[LocalDate]
  def parseActivityLine(s: String): ParserResult[ActivityLine]
  def parseBrakeLine(s: String): ParserResult[BrakeLine]
  def parseLine(s: String): ParserResult[Line]
}
