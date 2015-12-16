package com.kastrull.tajm.calculate

import com.kastrull.tajm.model.Activity
import com.kastrull.tajm.model.Work

object EffortCalculator {
  type EffortMap = Map[Activity, Int]

  def apply(): EffortCalculator = BasicEffortCalculator
}

trait EffortCalculator {
  def calculate(items: Seq[Work]): EffortCalculator.EffortMap
}

case object BasicEffortCalculator extends EffortCalculator {
  import EffortCalculator._

  def calculate(item: Work): Int = {
    val from = item.range.from
    val to = item.range.to
    val duration = to.minutes - from.minutes
    duration
  }

  def calculate(items: Seq[Work]): EffortMap = {
    val durations: Seq[(Activity, Int)] = items.map(item => (item.activity, calculate(item)))

    val byActivityDurations = durations.groupBy(_._1)

    byActivityDurations.map {
      case (activity, acivityDurationTuples) =>
        val durations: Seq[Int] = acivityDurationTuples.map(_._2)
        val totalDuration: Int = durations.sum
        (activity, totalDuration)
    }
  }
}
