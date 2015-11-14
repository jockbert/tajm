package com.kastrull.tajm.parse

import org.scalatest._

class CommentParserTest
    extends ParserTestFixture[Option[String]] {

  val parser = Parser.comment

  "should accept anything" in {
    "\"\"" becomes Some("")
    "\"\r\n\"" becomes Some("")
    "\"xxx\n\"" becomes Some("xxx")
    "\"hejkon bejkon\n\"" becomes Some("hejkon bejkon")
    "\"a\nb\tc\"" becomes Some("a\nb\tc")

  }

  "should trim extra whitespace" in {
    " \"\" " becomes Some("")
    " \"xxx\"" becomes Some("xxx")
    "\"xxx\" " becomes Some("xxx")
    " \"   x  x  x   \"  " becomes Some("x  x  x")
    "\t\"\txxx\t\r\"\t" becomes Some("xxx")
  }

  "no comment" in {
    "" becomes None
    "  \t \t " becomes None
  }

  "failure" in {
    "not a comment" fails ()
    " not a comment " fails ()
    "\"not a comment" fails ()
    "not a comment\"" fails ()
  }
}

// FIXME Update behavior and change from test to property
