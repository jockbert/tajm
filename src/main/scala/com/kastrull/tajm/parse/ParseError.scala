package com.kastrull.tajm.parse

trait ParseError {
  def source: String
  def position: Int
  def create(source: String, position: Int): ParseError
}

case class TimeFormatError(
  source: String,
  position: Int) extends ParseError {
  
  def create(s: String, p: Int) =
    TimeFormatError(s, p)
}

case class MissingTimeRangeError(
  source: String,
  position: Int) extends ParseError {
  
  def create(s: String, p: Int) =
    MissingTimeRangeError(s, p)
}
