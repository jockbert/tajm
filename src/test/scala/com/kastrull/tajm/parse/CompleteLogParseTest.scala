package com.kastrull.tajm.parse

import com.github.nscala_time.time.Imports._
import com.kastrull.tajm._
import scala.language.postfixOps

class CompleteLogParseTest
    extends ParserTestFixture[Seq[Day]] {

  def parser = Parser.completeLog

  val input = """
    |2014-05-06
    |	work / 1..2
    |
    |2014-05-07
    |	work /	1..2
    |
    |2014-05-08
    |	work  01..2:00
    |
    |""".stripMargin

  "CompleteLog" in {

    val a = Activity()
    val items = Work(a, TimeRange(1 h, 2 h)) :: Nil
    val day1 = Day(new LocalDate(2014, 5, 6), items)
    val day2 = Day(new LocalDate(2014, 5, 7), items)
    val day3 = Day(new LocalDate(2014, 5, 8), items)

    val expected = day1 :: day2 :: day3 :: Nil

    input becomes expected
  }
}

// FIXME Update behavior and change from test to property
