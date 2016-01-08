package com.kastrull.tajm.model

sealed trait ActivityParameter

case class ExpectedTime(expected: Time, todayOnly: Boolean)
  extends ActivityParameter

case class AccumulatedDiff(diff: Time)
  extends ActivityParameter

case object UnexpectTime
  extends ActivityParameter
