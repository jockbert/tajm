package com.kastrull.tajm.parse

import com.kastrull.tajm._
import scala.util.parsing.combinator._
import org.joda.time.LocalDate

@Deprecated
object LegacyParser extends JavaTokenParsers {

  def time: Parser[Time] = {
    def intOrEmpty: Parser[Int] = int | empty

    def empty: Parser[Int] = "" ^^ { case _ => 0 }

    def int: Parser[Int] = wholeNumber ^^ { case i => i.toInt }

    def hourOnlyTime: Parser[Time] =
      intOrEmpty ^^ { case h => Time(h * 60) }

    def hourMinuteTime: Parser[Time] =
      intOrEmpty ~ ":" ~ intOrEmpty ^^ { case h ~ _ ~ m => Time(h * 60 + m) }

    def minutes: Parser[Time] =
      intOrEmpty <~ ("""(?i)m(in(ute)?)?s?""".r) ^^ { case m => Time(m) }

    hourMinuteTime | minutes | hourOnlyTime ^^ { case t => t }
  }

  def timeRange: Parser[TimeRange] =
    time ~ ".." ~ time ^^ { case t1 ~ _ ~ t2 => TimeRange(t1, t2) }

  def activity: Parser[Activity] = {
    def optionalEnd: Parser[Unit] = """/?""".r ^^ { case _ => () }

    rep("/" ~> ident) <~ optionalEnd ^^ { case s => Activity(s) }
  }

  def someComment: Parser[Some[String]] = "\"" ~> """[^\"]*""".r <~ "\"" ^^ { case text => Some(text.trim()) }

  def comment: Parser[Option[String]] = {
    def none: Parser[Option[String]] = "" ^^ { case _ => None }
    someComment | none
  }

  def work: Parser[Work] =
    "work" ~> activity ~ timeRange ~ comment ^^ { case a ~ tr ~ c => Work(a, tr, c) }

  def expect: Parser[Expect] = {
    def period: Parser[Expect.Period] = ("day" | "week") ^^ {
      case "day"  => Expect.DAY
      case "week" => Expect.WEEK
    }

    "expect" ~> activity ~ period ~ time ~ comment ^^ { case a ~ p ~ t ~ c => Expect(a, t, p, c) }
  }

  def unexpect: Parser[Unexpect] =
    "unexpect" ~> activity ~ comment ^^ { case a ~ c => Unexpect(a, c) }

  def accDiff: Parser[AccumulatedDiff] =
    "accdiff" ~> activity ~ time ~ comment ^^ { case a ~ t ~ c => AccumulatedDiff(a, t, c) }

  def note: Parser[Note] = someComment ^^ { case s => Note(s) }

  def date: Parser[LocalDate] = {
    def oneDigit = """\d""".r ^^ { case s => s.toInt }
    def twoDigits = """\d\d""".r ^^ { case s => s.toInt }
    def fourDigits = """\d\d\d\d""".r ^^ { case s => s.toInt }

    def delimiter = """-?""".r ^^ { case _ => () }
    def year = fourDigits | twoDigits ^^ { case i => i }
    def month = twoDigits | oneDigit ^^ { case s => s.toInt }
    def day = twoDigits | oneDigit ^^ { case s => s.toInt }

    def addCentury(year: Int) = year + (if (year > 100) 0 else if (year > 70) 1900 else 2000)

    def y4md = fourDigits ~ delimiter ~ month ~ delimiter ~ day ^^ { case y ~ _ ~ m ~ _ ~ d => new LocalDate(y, m, d) }
    def y2md = twoDigits ~ delimiter ~ month ~ delimiter ~ day ^^ { case y ~ _ ~ m ~ _ ~ d => new LocalDate(addCentury(y), m, d) }

    def dmy = day ~ "/" ~ month ~ delimiter ~ year ^^ { case d ~ _ ~ m ~ _ ~ y => new LocalDate(addCentury(y), m, d) }

    y4md | y2md | dmy
  }

  def day: Parser[Day] =
    date ~ comment ~ rep(accDiff | work | expect | unexpect | note) ^^
      { case date ~ comment ~ seq => Day(date, seq, comment) }

  def completeLog: Parser[Seq[Day]] = rep(day)
}
