package com.kastrull.tajm.parse

import scala.util.parsing.combinator.RegexParsers
import scala.language.postfixOps
import com.kastrull.tajm.model._
import org.joda.time.LocalDate

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

  def expectedTime: Parser[ExpectedTime] =
    "expect" ~> time ~ flag("once") ^^ { s => ExpectedTime(s._1, s._2) }

  def timeRange: Parser[TimeRange] =
    (time <~ "-") ~ time ^^ { s => TimeRange(s._1, s._2) }

  def comment: Parser[Comment] =
    "# .*".r ^^ { s => Comment(s.tail.tail) }

  def accumulatedDiff: Parser[AccumulatedDiff] =
    "diff" ~> time ^^ { AccumulatedDiff(_) }

  def unexpectTime: Parser[UnexpectTime.type] =
    "unexpect" ^^^ { UnexpectTime }

  def brake: Parser[Brake] =
    "(brake|lunch)".r ^^ { s => Brake(s == "lunch") }

  def date: Parser[LocalDate] =
    (((int <~ "-") ~ int <~ "-") ~ int) ^^ {
      case ~(~(y, m), d) => new LocalDate(y, m, d)
    }

  def parameter: Parser[Parameter] =
    accumulatedDiff | unexpectTime | expectedTime

  def activityLine: Parser[ActivityLine] =
    activity ~ (timeRange ?) ~ (parameter *) ^^ {
      case ~(~(act, dur), params) => ActivityLine(act, dur, params)
    }
}
