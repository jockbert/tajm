package com.kastrull.tajm.parse

import org.scalatest._
import com.kastrull.tajm.ast.Comment

class CommentParserTest extends ParserTestFixture[Comment] {

  val parser = CommentParser()

  "CommentParser" - {

    "should accept anything" in {
      "" becomes Comment("")
      "xxx" becomes Comment("xxx")
      "hejkon bejkon" becomes Comment("hejkon bejkon")
      "a\nb\tc" becomes Comment("a\nb\tc")
    }

    "should trim extra whitespace" in {
      " " becomes Comment("")
      " xxx" becomes Comment("xxx")
      "xxx " becomes Comment("xxx")
      "  x  x  x  " becomes Comment("x  x  x")
      "\txxx\t" becomes Comment("xxx")
    }
    
    
  }
}

