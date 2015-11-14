package com.kastrull.tajm

import org.scalatest.FreeSpec
import org.scalacheck._
import org.scalatest.Matchers

class ActivityProperies extends Properties("Activity") {
  import Prop.forAll
  import Prop.BooleanOperators
  import scala.language.postfixOps

  property("isChildOf") = forAll { (a: Seq[String], b: Seq[String]) =>
    (b.length > 0) ==> {
      val parent = Activity(a: _*)
      val child = Activity((a ++ b): _*)
      child.isChildOf(parent)
    }
  }

  property("isParentOf") = forAll { (a: Seq[String], b: Seq[String]) =>
    val parent = Activity(a: _*)
    val child = Activity((a ++ b): _*)
    parent.isParentOf(child)
  }

}

class ActivityTest extends FreeSpec with Matchers {

  val c = Activity("c")
  val C = Activity("C")
  val cd = Activity("c", "d")
  val b = Activity("b")
  val root = Activity()

  "has a path / name" in {
    assert(c.name === Seq("c"))
    assert(cd.name === Seq("c", "d"))
    assert(root.name === Seq())
  }

  "can have children" in {

    assert(cd.isChildOf(c))
    assert(c.isChildOf(root))
    assert(cd.isChildOf(root))

    assert(!c.isChildOf(cd))
    assert(!c.isChildOf(c))
    assert(!C.isChildOf(cd))
    assert(!root.isChildOf(c))
    assert(!b.isChildOf(cd))
  }

  "can have parent" in {

    assert(c.isParentOf(cd))
    assert(root.isParentOf(c))
    assert(root.isParentOf(cd))

    assert(!cd.isParentOf(c))
    assert(!c.isParentOf(c))
    assert(!C.isParentOf(cd))
    assert(!c.isParentOf(root))
    assert(!b.isParentOf(cd))
  }
}

// FIXME Update behavior and change from test to property
