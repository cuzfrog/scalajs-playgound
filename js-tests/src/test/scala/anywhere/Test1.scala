package anywhere

import com.github.cuzfrog.IconvFacade
import com.github.cuzfrog.mytest.MyTestSuite


object Test1 extends MyTestSuite{
  test("this is my first test"){
    println("do some test1!")
  }

//  test("this is a failed test"){
//    throw new AssertionError("manual failure.")
//  }
//

//
//  test("dom test"){
//    val div = org.scalajs.dom.document.createElement("div")
//    val body = org.scalajs.dom.document.body.appendChild(div)
//
//  }
}
