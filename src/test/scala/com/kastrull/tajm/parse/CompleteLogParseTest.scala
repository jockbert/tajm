package com.kastrull.tajm.parse

import org.scalatest.FreeSpec
import org.scalatest.Matchers
import com.kastrull.tajm.parse.Parser._
import com.github.nscala_time.time.Imports._
import com.kastrull.tajm.ast.Work
import com.kastrull.tajm.ast.Activity
import com.kastrull.tajm.ast.TimeRange
import com.kastrull.tajm.ast.Time
import com.kastrull.tajm.ast.WorkDay

class CompleteLogParseTest extends FreeSpec with Matchers {

  val logExample1 = """
    |2014-05-06
    |
    |	expect /neta/tam 	day 4 
    |	expect / 		week 40
    |	diff / 			32:30 
    |
    |	work /neta/tam/case24650 	8:45..9:00 	# A problem fixed
    |	work /neta/tam/case12345 	9..12
    |	work /utb/emblinux 		13..15		# Session 2
    |
    |2014-05-07
    |	work /neta/tam/case12345 	9..17
    |	lunch 11..12
    |	break 14..14:15
    |
    |2014-05-08
    |
    |	unexpect /neta/tam 
    |
    |	work /neta/tam/case30245	7:30..12:30
    |	work '' 			13:45..18:30 # Long day
    |
    |2014-05-09
    |	# Another day of work 
    |
    |	work /neta/tam/caseA	7:30..12:30
    |	work /neta/tam/caseB	9:45..10:30 # In the middle
    |""".stripMargin

  "CompleteLog" - {

    val parser = new CompleteLogParser()

    "work" in {
      val input = """  
        |2014-05-06
    	|
    	|	work /A 	8:45..9:00
    	|	work /B 	9..12
        |
        |""".stripMargin
        
      val date = new LocalDate(2014, 5, 6)
      val workA = Work(Activity("A"), TimeRange(Time(8 * 60 + 45), Time(9 * 60)))
      val workB = Work(Activity("B"), TimeRange(Time(9 * 60), Time(12 * 60)))
      val day = WorkDay(date, Seq(workA,workB))
      val expected = Seq(day)
      
      val actualResult = parser(input)
      assert(actualResult.hasCommand)
      val actual = actualResult.command
      assert(actual === expected)
    }

    "missing date context" ignore {

    }
  }

}