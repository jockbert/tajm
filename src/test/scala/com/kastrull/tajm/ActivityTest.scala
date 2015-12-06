package com.kastrull.tajm

import org.scalacheck.Prop.forAll
import org.scalacheck.Prop.propBoolean
import org.scalacheck.Properties

class ActivityProperies extends Properties("Activity") {
  type Path = Seq[String]

  property("isChildOf") = forAll { (a: Path, b: Path) =>
    (a.startsWith(b) && a.length > 0) == Activity(a).isChildOf(Activity(b))
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
