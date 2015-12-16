package com.kastrull.tajm.model

import org.scalatest.FreeSpec
import org.scalatest.Matchers

class WorkTest extends FreeSpec with Matchers {

  "has activity" in {
    val activity = Activity("a")
    val work = Work(activity)
    assert(work.activity === activity)
  }

  "has time range" in {
    val activity = Activity("a")
    val range = TimeRange(Time(1), Time(2))
    val work = Work(activity, range)
    assert(work.range === range)
  }

  "can have comment" in {
    val activity = Activity("a")
    val range = TimeRange(Time(1), Time(2))
    val comment = Some("hello")
    val work1 = Work(activity, range, comment)
    val work2 = Work(activity, range)
    assert(work1.comment === comment)
    assert(work2.comment === None)
  }
}

// FIXME Update behavior and change from test to property
