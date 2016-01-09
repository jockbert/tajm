package com.kastrull.tajm

import scala.io.Source
import java.io.FileReader
import com.kastrull.tajm.parse._

object ConsoleTajm extends App {
  val fileName = args(0)

  val dataSource = Source.fromFile(fileName)
  val parser: Parser = NormalFormParser

  for (line <- dataSource.getLines()) {
    println(parser.parseLine(line))
  }
  // TODO create program
}
