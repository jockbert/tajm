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

  def hours: Parser[Hours] =
    """\d+""".r ^^ { s => Hours(s.toInt) }

  def minutes: Parser[Minutes] =
    """\d+""".r <~ "m".r ^^ { s => Minutes(s.toInt) }

}
