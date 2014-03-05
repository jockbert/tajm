package com.kastrull.tajm.parse

import com.kastrull.tajm.ast.Command

trait Parser {
	type Commands = Seq[Command]
	type ParseErrors = Seq[ParseError]
	type Result = Either[Commands,ParseErrors]
  
	def parse(src: String): Result
}