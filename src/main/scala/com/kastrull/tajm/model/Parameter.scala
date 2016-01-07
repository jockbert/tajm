package com.kastrull.tajm.model

sealed trait Parameter

case class ExpectedTime(
  expected: Time,
  todayOnly: Boolean) extends Parameter

case class AccumulatedDiff(diff: Time) extends Parameter

case object UnexpectTime extends Parameter
