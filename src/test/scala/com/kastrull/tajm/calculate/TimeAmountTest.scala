package com.kastrull.tajm.calculate

import org.scalatest.FreeSpec
import org.scalatest.Matchers
import com.kastrull.tajm.Activity
import org.joda.time.LocalDate

class TimeAmountTest
    extends FreeSpec
    with Matchers {

  val march7 = new LocalDate(2014, 3, 7)
  val march8 = new LocalDate(2014, 3, 8)
  val march9 = new LocalDate(2014, 3, 9)
  val march30 = new LocalDate(2014, 3, 30)
  val march31 = new LocalDate(2014, 3, 31)
  val april1 = new LocalDate(2014, 4, 1)

  "empty" in {
    val ammount = TimeAmmount()
    assert(ammount.definedFrom() === None)
    assert(ammount.definedTo() === None)
    assert(ammount.minutes(march7) === None)
    assert(ammount.minutes(march8) === None)
    assert(ammount.minutes(march9) === None)
  }

  "zero ammount" in {
    val ammount = TimeAmmount(
      march8 -> None)

    assert(ammount.definedFrom() === None)
    assert(ammount.definedTo() === None)
    assert(ammount.minutes(march7) === None)
    assert(ammount.minutes(march8) === None)
    assert(ammount.minutes(march9) === None)
  }

  "one day ammount" in {
    val ammount = TimeAmmount(
      march8 -> Some(10),
      march9 -> None)

    assert(ammount.definedFrom() === Some(march8))
    assert(ammount.definedTo() === Some(march9))
    assert(ammount.minutes(march7) === None)
    assert(ammount.minutes(march8) === Some(10))
    assert(ammount.minutes(march9) === None)
  }

  "one day unclosed" in {
    val ammount = TimeAmmount(
      march8 -> Some(0))

    assert(ammount.definedFrom() === Some(march8))
    assert(ammount.definedTo() === None)
    assert(ammount.minutes(march7) === None)
    assert(ammount.minutes(march8) === Some(0))
    assert(ammount.minutes(march9) === Some(0))
    assert(ammount.minutes(april1) === Some(0))
  }

  "unsorted" in {
    val ammount = TimeAmmount(
      march9 -> None,
      march8 -> Some(10))

    assert(ammount.definedFrom() === Some(march8))
    assert(ammount.definedTo() === Some(march9))
    assert(ammount.minutes(march7) === None)
    assert(ammount.minutes(march8) === Some(10))
    assert(ammount.minutes(march9) === None)
  }

  "same but with different input " in {
    val a1 = TimeAmmount(
      march9 -> None,
      march7 -> Some(10))

    val a2 = TimeAmmount(
      march7 -> Some(10),
      march9 -> None,
      march8 -> Some(10))

    val a3 = TimeAmmount(
      march7 -> Some(10),
      march9 -> None)

    val other = TimeAmmount(
      march7 -> Some(9),
      march9 -> None)

    assert(a1 === a2)
    assert(a1 === a3)
    assert(a2 === a3)

    assert(other !== a1)
    assert(other !== a2)
    assert(other !== a3)
  }

  "range of dates" in {
    val ammount = TimeAmmount(
      march8 -> Some(13),
      april1 -> None)

    assert(ammount.definedFrom() === Some(march8))
    assert(ammount.definedTo() === Some(april1))
    assert(ammount.minutes(march7) === None)
    assert(ammount.minutes(march8) === Some(13))
    assert(ammount.minutes(march9) === Some(13))
    assert(ammount.minutes(march30) === Some(13))
    assert(ammount.minutes(march31) === Some(13))
    assert(ammount.minutes(april1) === None)
  }

  "different consecutive ammounts" in {
    val ammount = TimeAmmount(
      march9 -> Some(23),
      march8 -> Some(14),
      march31 -> Some(5),
      april1 -> None)

    assert(ammount.definedFrom() === Some(march8))
    assert(ammount.definedTo() === Some(april1))
    assert(ammount.minutes(march7) === None)
    assert(ammount.minutes(march8) === Some(14))
    assert(ammount.minutes(march9) === Some(23))
    assert(ammount.minutes(march30) === Some(23))
    assert(ammount.minutes(march31) === Some(5))
    assert(ammount.minutes(april1) === None)
  }

}

// FIXME Update behavior and change from test to property
