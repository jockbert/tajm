package com.kastrull.tajm.model

case class ExpectedTime(
  expected: Time,
  todayOnly: Boolean)

case class AccumulatedDiff(diff: Time)
