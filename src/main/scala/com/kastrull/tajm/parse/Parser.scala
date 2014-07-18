package com.kastrull.tajm.parse

import com.kastrull.tajm.ast.Comment

object Parser {

  type Result[T] = Either[T, ParseError]

  val comment = CommentParser().apply _

  implicit class RichResult[T](result: Result[T]) {
    def hasCommand: Boolean = result.isLeft
    def hasParseErrors: Boolean = result.isRight
    def command: T = result.left.get
    def error: ParseError = result.right.get
  }
}

trait Parser[T ]{
  import Parser._
  def apply(src: String): Result[T]
}

case class CommentParser() extends Parser[Comment] {
  def apply(src: String): Parser.Result[Comment] =
    Left(Comment(src.trim()))
}