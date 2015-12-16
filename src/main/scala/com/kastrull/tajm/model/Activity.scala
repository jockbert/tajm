package com.kastrull.tajm.model

object Activity {
  def apply(): Activity = Activity(Nil)
  def apply(p1: String): Activity = Activity(p1 :: Nil)
  def apply(p1: String, p2: String): Activity = Activity(p1 :: p2 :: Nil)
  def apply(p1: String, p2: String, p3: String): Activity = Activity(p1 :: p2 :: p3 :: Nil)
}

case class Activity(path: Seq[String]) {

  def isParentOf(other: Activity): Boolean = {
    val depth = path.length
    def otherIsLonger = other.path.length > depth
    def equalPrefix = other.path.take(depth) == path

    otherIsLonger && equalPrefix
  }

  def isChildOf(other: Activity) =
    other.isParentOf(this)

  override def toString(): String =
    "Activity(" + path.map("'" + _ + "'").mkString(", ") + ")"

}
