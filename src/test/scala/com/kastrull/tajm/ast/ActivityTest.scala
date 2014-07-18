package com.kastrull.tajm.ast

import org.scalatest.FreeSpec
import org.scalatest.Matchers

class ActivityTest extends FreeSpec with Matchers {

  "Activity" - {

    val c = Activity("c")
    val C = Activity("C")
    val cd = Activity("c", "d")
    val b = Activity("b")
    val root = Activity()

    "has a path / name" - {
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
}