package com.kastrull.tajm.ast

import org.scalatest.FreeSpec
import org.scalatest.Matchers

class ActivityTest extends FreeSpec with Matchers {

  "Activity" - {
    "has a name" in {
      val name = "some name"
      val activity = Activity(name)
      assert(activity.name === name) 
    }
    
    "has a path" ignore {
      val name = "name"
        val path = "/name" 
        val activity = Activity(name)
       // assert(activity.path === path)
    }
    
    "has children" in {
      val c1 = Activity("c1")
      val c2 = Activity("c2")
      val parent = Activity("parent").child(c1).child(c2)
      
      val children = c1 :: c2 :: Nil
      assert(children ===  parent.children)
      
    }
    
    "can have parent" in {
      val c = Activity("c")
      assert(c.parent === None)
      
      val p = Activity("p")
      p.child(c)
      assert(c.parent === Some(p))
    }
  }
}