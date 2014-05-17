package com.kastrull.tajm.parse

case class TestError(
  source: String, position: Int) extends ParseError {
  def create(s: String, p: Int) = TestError(s, p)
}