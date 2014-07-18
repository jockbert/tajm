package com.kastrull.tajm.parse

import org.scalatest.FreeSpec
import org.scalatest.Matchers

import com.kastrull.tajm.parse.Parser.RichResult

object ParserTestFixture {
  
}

trait ParserTestFixture[T] 
extends FreeSpec with Matchers {

  import ParserTestFixture._
  
  implicit class ResultAsserter(source: String) {

    def becomes(expected: T): Unit = {
      val actualResult = parser(source)
      val command = actualResult.command
      assert(command == expected)
    }

    def becomes(expected: ParseError): Unit = {
      val actualResult = parser(source)
      val error = actualResult.error
      assert(error == expected)
    }
  }

  def parser: Parser[T]
}