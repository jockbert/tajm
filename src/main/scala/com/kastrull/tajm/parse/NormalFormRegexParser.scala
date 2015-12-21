package com.kastrull.tajm.parse

import scala.util.parsing.combinator.RegexParsers
import scala.language.postfixOps
import com.kastrull.tajm.model._

protected case object NormalFormRegexParser extends RegexParsers {

  def activity: Parser[Activity] =
    """(\/[^\/\s]*)+""".r ^^
      {
        _ match {
          case "/"  => Activity()
          case path => Activity(path.drop(1).split('/').toList)
        }
      }

  def int: Parser[Int] = """\d+""".r ^^ { _.toInt }

  def flag(f: String): Parser[Boolean] = (f ?) ^^ { _.isDefined }

  def hours: Parser[Hours] = int ^^ { Hours(_) }

  def minutes: Parser[Minutes] = int <~ "m" ^^ { Minutes(_) }

  def clock: Parser[Clock] =
    (int <~ ':') ~ int ^^ { s => Clock(s._1, s._2) }

  def time: Parser[Time] = (clock | minutes | hours)

  def expected: Parser[ExpectedTime] =
    "expect" ~> time ~ flag("once") ^^ { s => ExpectedTime(s._1, s._2) }

}
