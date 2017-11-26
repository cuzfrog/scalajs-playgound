package com.github.cuzfrog

import scala.scalajs.js
import utest._

object IconvFacadeTest extends TestSuite {

  val tests = this {
    'test1 {
      val s1 = "中文"
      val buf = IconvFacade.encode(s1, "GBK")
      IconvFacade.decode(buf,"GBK")
      //IconvFacade.decode(buf,"UTF8")
    }
  }
}
