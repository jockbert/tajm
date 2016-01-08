package com.kastrull.tajm.model

import org.joda.time.LocalDate

case class Line(content: Content, comment: Option[Comment])

case class Comment(text: String)

sealed trait Content

case object EmptyLine
  extends Content

case class DateLine(date: LocalDate)
  extends Content

case class BrakeLine(brake: Brake, duration: TimeRange)
  extends Content

case class ActivityLine(activity: Activity, duration: Option[TimeRange], params: Seq[ActivityParameter])
  extends Content
