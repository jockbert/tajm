package com.kastrull.tajm.model

case class Line(content: LineContent, comment: Comment)

sealed trait LineContent

case object EmptyLine extends LineContent
case class BreakLine() extends LineContent

case class ActivityLine(
  activity: Activity,
  duration: Option[TimeRange],
  parameters: Seq[Parameter]) extends LineContent
