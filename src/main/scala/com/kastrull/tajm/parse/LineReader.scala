package com.kastrull.tajm.parse

import com.kastrull.tajm.model.Line

object LineReader {

  def apply(parser: Parser): LineReader = new DefaultLineReader(parser)

  private class DefaultLineReader(parser: Parser) extends LineReader {

    def read(lineIt: Iterator[String]): ParserResult[Seq[Line]] = {

      def stripParseResult(resultIt: Iterator[ParserResult[Line]]): ParserResult[List[Line]] =
        if (!resultIt.hasNext) Left(Nil)
        else resultIt.next() match {
          case Right(message) => Right(message)
          case Left(line) =>
            stripParseResult(resultIt) match {
              case Left(lines) => Left(line :: lines)
              case right       => right
            }
        }

      val resultIt = lineIt.map(parser.parseLine)
      stripParseResult(resultIt)
    }

  }

}

trait LineReader {
  def read(lineIt: Iterator[String]): ParserResult[Seq[Line]]
}
