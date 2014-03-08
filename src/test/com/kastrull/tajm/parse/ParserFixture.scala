package com.kastrull.tajm.parse

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import com.kastrull.tajm.ast.Command
import com.kastrull.tajm.parse.Parser._

object ParserFixture {
  
}

trait ParserFixture extends FlatSpec with Matchers {
  import ParserFixture._
  
  implicit class ResultAsserter(source: String) {

    def becomes(expected: Command): Unit = {
      val actualResult = parser(source)
      val command = actualResult.command
      assert(command == expected)
    }

    def becomes(expected: ParseError*): Unit = {
      val actualResult = parser(source)
      val errors = actualResult.errors
      assert(errors == expected.toSeq)
    }
  }

  def parser: Parser
}