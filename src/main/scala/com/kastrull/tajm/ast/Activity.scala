package com.kastrull.tajm.ast


case class Activity(val name: String, _children: Seq[Activity] = Nil){
  
  private var privParent: Option[Activity] = None
  private var privChildren = _children

  def child(child: Activity)  = {
    privChildren = privChildren :+ child 
    child.privParent = Some(this)
    this
  }
  
  def parent = privParent
  def children = privChildren
}