package com.kastrull.tajm.model

import org.joda.time.LocalDate

case class ContentLine(content: Content, comment: Option[Comment])

sealed trait Content

case object EmptyLine extends Content

case class DateLine(date: LocalDate) extends Content

case class BrakeLine(
  brake: Brake,
  duration: TimeRange) extends Content

case class ActivityLine(
  activity: Activity,
  duration: Option[TimeRange],
  parameters: Seq[Parameter]) extends Content
