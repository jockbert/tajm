package com.kastrull.tajm.parse

trait ParseError {
  def source: String
  def position: Int
}

case class TimeFormatError(
    source: String, 
    position: Int) extends ParseError
