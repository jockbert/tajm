package com.kastrull.tajm

import org.scalacheck.Prop.forAll
import org.scalacheck.Prop.propBoolean
import org.scalacheck.Properties

class ActivityProps extends Properties("Activity") {
  type Path = Seq[String]

  property("isChildOf") = forAll { (a: Path, b: Path) =>
    val child = Activity(a ++ b)
    val parent = Activity(a)
    child.isChildOf(parent) || b.isEmpty
  }

  property("isParentOf") = forAll { (a: Path, b: Path) =>
    val parent = Activity(a)
    val child = Activity(a ++ b)
    parent.isParentOf(child) || b.isEmpty
  }

  property("equals") = forAll { p: Path =>
    Activity(p) == Activity(p)
  }

  property("name") = forAll { p: Path =>
    Activity(p).name == p
  }
}
