package com.kastrull.tajm.parse

import org.joda.time.LocalDate

class DateParserTest extends ParserTestFixture[LocalDate] {

  def parser = Parser.date

  "Complete" in {
    "20140102" becomes new LocalDate(2014, 1, 2)
    "20140304" becomes new LocalDate(2014, 3, 4)
    "1998-05-06" becomes new LocalDate(1998, 5, 6)
    "1998 12 07" becomes new LocalDate(1998, 12, 7)
  }
  "Compact" in {
    "140102" becomes new LocalDate(2014, 1, 2)
    "140102" becomes new LocalDate(2014, 1, 2)
    "140304" becomes new LocalDate(2014, 3, 4)
    "98-05-06" becomes new LocalDate(1998, 5, 6)
    "98-5-6" becomes new LocalDate(1998, 5, 6)
    "98 2 07" becomes new LocalDate(1998, 2, 7)
    "98 12-7" becomes new LocalDate(1998, 12, 7)
  }
  "Reversed" in {
    "02/01-2014" becomes new LocalDate(2014, 1, 2)
    "2/1-14" becomes new LocalDate(2014, 1, 2)
    "4/3 14" becomes new LocalDate(2014, 3, 4)
    "06/05 -98" becomes new LocalDate(1998, 5, 6)
    "98-5-6" becomes new LocalDate(1998, 5, 6)
    "98 2 07" becomes new LocalDate(1998, 2, 7)
    "7/12 1998" becomes new LocalDate(1998, 12, 7)
  }
}

// FIXME Update behavior and change from test to property
