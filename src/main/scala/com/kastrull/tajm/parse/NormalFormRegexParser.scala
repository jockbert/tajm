package com.kastrull.tajm.parse

import scala.util.parsing.combinator.RegexParsers
import com.kastrull.tajm.Activity

protected case class NormalFormRegexParser() extends RegexParsers {

  def activity: Parser[Activity] = """\/|(\/[^\/\s]+)+""".r ^^
    { s => Activity(s.drop(1)) }

}
