package com.kastrull.tajm.parse

import scala.util.parsing.combinator.RegexParsers
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

  def int: Parser[Int] =
    """\d+""".r ^^ { s => s.toInt }

  def hours: Parser[Hours] =
    int ^^ { h => Hours(h) }

  def minutes: Parser[Minutes] =
    int <~ "m" ^^ { m => Minutes(m) }

  def clock: Parser[Clock] =
    (int <~ ':') ~ int ^^ { s => Clock(s._1, s._2) }

}
