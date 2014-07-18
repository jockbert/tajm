package com.kastrull.tajm.ast

import org.scalatest.FreeSpec
import org.scalatest.Matchers

class WorkTest extends FreeSpec with Matchers {
  
  "Work" - {
    "has activity" - {
      val activity = Activity("a")
      val work = Work(activity)
      
      assert(work.activity === activity)
      
    }
    
    "has time range" ignore {
      //FIXME
    }
    
    "can have comment" ignore {
      //FIXME
    }
    
  }

}