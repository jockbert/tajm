package com.kastrull.tajm.parse

import com.kastrull.tajm.ast.LineCommand
import com.kastrull.tajm.parse.Parser.Result
import scala.util.parsing.combinator._




class CompleteLogParser extends Parser[Seq[LineCommand]] {

  def apply(src: String): Result[Seq[LineCommand]] = { null }

}