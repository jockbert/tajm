package com.kastrull.tajm.parse

import com.kastrull.tajm.ast._

case class ActivityParser() extends Parser[Activity] {

  def apply(src: String): Parser.Result[Activity] = {
    val trimmed = src.trim()

    def removeEndSlash(s: String) =
      removeStartSlash(s.reverse).reverse

    def removeStartSlash(s: String) =
      if (s == "/") ""
      else if (s.head == '/') s.tail
      else s

    val stripped = removeStartSlash(trimmed)

    val splitted = stripped.split('/').toSeq

    Left(Activity(splitted: _*))

  }
}