package com.kastrull.tajm.calculate

import com.kastrull.tajm.Activity
import com.kastrull.tajm.Work

object EffortCalculator {
  type EffortMap = Map[Activity, Int]

  def appy():EffortCalculator = BasicEffortCalculator()
}

trait EffortCalculator {
  def calculate(items: Seq[Work]): EffortCalculator.EffortMap
}

case class BasicEffortCalculator extends EffortCalculator {
  import EffortCalculator._

  def calculate(item: Work): Int = {
    val from = item.range.from
    val to = item.range.to
    val duration = to.minutes-from.minutes
    duration
  }
  
  def calculate(items: Seq[Work]): EffortMap = {
    val durations = items.map(item => (item.activity, calculate(item)))
    
    val byActivityDurations = durations.groupBy(_._1)
    
    val activityTotalDuration = byActivityDurations.map{ 
      case (activity, acivityDurationTuples) =>
        val durations = acivityDurationTuples.map(_._2)
      	val totalDuration = durations.sum
      	(activity, totalDuration)
    }
    activityTotalDuration
  }
}