package com.kastrull.tajm.parse

import com.kastrull.tajm.Note

class NoteParserTest extends ParserTestFixture[Note] {

  def parser = LegacyParser.note

  "a note" in {
    """ "a b c" """ becomes Note(Some("a b c"))
  }

  "no note" in {
    """         """ fails ()
    """  a b c  """ fails ()
  }
}

// FIXME Update behavior and change from test to property
