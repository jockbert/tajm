package com.kastrull.tajm.parse

import org.scalatest.FreeSpec
import org.scalatest.Matchers
import scala.util.parsing.combinator._
import com.kastrull.tajm.parse.LegacyParser._
import com.kastrull.tajm.model._

trait ParserTestFixture[T]
    extends FreeSpec
    with Matchers {

  implicit class ImplicitTime(hour: Int) {
    def h = Hours(hour * 60)
    def h(minutes: Int) = Clock(hour, minutes)
  }

  implicit class ResultAsserter(source: String) {

    def becomes(expected: T): Unit = {
      val actualResult = parseAll(parser, source)
      actualResult match {
        case Success(actual, _) =>
          assert(actual === expected)
        case other =>
          fail("Not expecting " + other)
      }

      val command = actualResult.get

    }

    def fails(): Unit = {
      val actualResult = parseAll(parser, source)
      actualResult match {
        case Failure(message, next) => ()
        case other                  => fail("Not expecting " + other)
      }
    }
  }

  def parser: LegacyParser.Parser[T]
}

// FIXME Update behavior and change from test to property
