package com.kastrull.tajm.calculate

import org.scalatest.FreeSpec
import org.scalatest.Matchers
import com.kastrull.tajm.Activity
import org.joda.time.LocalDate
import TimeAmmount.DailyAmmounts
import TimeAmmount.DailyAmmount
import com.kastrull.tajm.Note
import com.kastrull.tajm.LineCommand
import com.kastrull.tajm.Work
import com.kastrull.tajm.TimeImplicits._
import com.kastrull.tajm.TimeRange
import com.kastrull.tajm.AccumulatedDiff
import com.kastrull.tajm.Expect
import com.kastrull.tajm.Unexpect

class ExpectedWorkConverterTest
    extends FreeSpec
    with Matchers {

  val march1 = new LocalDate(2014,3,1)
  val activity = Activity("a")
  
  trait Converter {
      val calculator = ExpectationConverter()
  }
  
  trait LineCommandTest extends Converter {
    def expected: Option[(Activity, Option[Int])]
    
    def test(cmd: LineCommand ) =     {
      val actual = calculator.calculateLineCommand(cmd)  
      assert(expected === actual)
    }
    
  }
  
  
  ".calculate()" - {
    "no expectations" in new Converter {

      val ammounts: Map[Activity, DailyAmmounts] = calculator.calculate()
      val expected = Map.empty[Activity, DailyAmmounts]
      assert(expected === ammounts)
    }
  }

  ".calculateOneActivity()" -{
    "one day" ignore new Converter {

    // val ammounts: DailyAmmounts = calculator.calculateOneActivity(
     //     Day(march1,Expect()))

      val expected = List.empty[DailyAmmount]

    //  assert(expected === ammounts)

    }
  }

  ".calculateLineCommand()" - {
    
    "Note" in new LineCommandTest {
      def expected = None
      test(Note(Some("Hello")))
    }
    
    "Work" in new LineCommandTest {
      def expected = None
      test(Work(activity,TimeRange(1,2)))
    }
    
    "AccumulatedDiff" in new LineCommandTest {
      def expected = None
      test(AccumulatedDiff(activity,1))
    }
    "Expect day" in new LineCommandTest {
      def expected = Some((activity,Some(60*6)))
      test(Expect(activity,6,Expect.DAY))
    }
    "Expect week" in new LineCommandTest {
      def expected = Some((activity,Some(60*8)))
      test(Expect(activity,40,Expect.WEEK))
    }
    
    "Unexpect" in new LineCommandTest {
      def expected = Some((activity,None))
      test(Unexpect(activity))
    }
  }

}