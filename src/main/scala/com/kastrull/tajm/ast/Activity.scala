package com.kastrull.tajm.ast

case class Activity(
    val name: String, 
    val children: Seq[Activity] = Nil){

  def child(child: Activity)  = Activity(name, children :+ child)
  
}