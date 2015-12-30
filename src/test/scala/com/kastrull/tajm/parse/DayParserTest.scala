package com.kastrull.tajm.parse

import com.kastrull.tajm.model.Day
import org.joda.time.LocalDate
import com.kastrull.tajm.model.Activity
import com.kastrull.tajm.model.AccumulatedDiffOld
import com.kastrull.tajm.model.Expect
import com.kastrull.tajm.model.Work
import com.kastrull.tajm.model.TimeRange
import com.kastrull.tajm.model.Unexpect
import com.kastrull.tajm.model.Note
import scala.language.postfixOps

class DayParserTest extends ParserTestFixture[Day] {

  def parser = LegacyParser.day

  "day of no item" in {
    "20140102" becomes Day(new LocalDate(2014, 1, 2))

    """ | 140721
      |
      | """.stripMargin becomes Day(new LocalDate(2014, 7, 21))
  }

  "day with comment and work" in {
    "20140102" becomes Day(new LocalDate(2014, 1, 2))

    """ | 140721 "monday"
      | work /a 1..2
      | """.stripMargin becomes
      Day(
        new LocalDate(2014, 7, 21),
        Work(Activity("a"), TimeRange(1 h, 2 h)) :: Nil,
        Some("monday"))
  }

  "day with all type of items" in {

    val a = Activity("a")

    """| 1/2 -14
       | expect /a day 4   "comment1"
       | accdiff /a 05:30   "comment2"
       | work /a 1..2    "comment3"
       | unexpect /a     "comment4"
       | "some note"
       |""".stripMargin becomes
      Day(new LocalDate(2014, 2, 1),
        Expect(a, 4 h, Expect.DAY, Some("comment1")) ::
          AccumulatedDiffOld(a, 5 h 30, Some("comment2")) ::
          Work(a, TimeRange(1 h, 2 h), Some("comment3")) ::
          Unexpect(a, Some("comment4")) ::
          Note(Some("some note")) ::
          Nil)
  }

}

// FIXME Update behavior and change from test to property
