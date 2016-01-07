package com.kastrull.tajm.model

case class ContentLine(content: Content, comment: Comment)

sealed trait Content

case object EmptyLine extends Content

case class BrakeLine(
  brake: Brake,
  duration: TimeRange) extends Content

case class ActivityLine(
  activity: Activity,
  duration: Option[TimeRange],
  parameters: Seq[Parameter]) extends Content
