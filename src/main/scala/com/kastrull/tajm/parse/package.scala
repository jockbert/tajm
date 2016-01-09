package com.kastrull.tajm

package object parse {
  type ParserResult[VAL] = Either[VAL, String]
}
