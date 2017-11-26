package anywhere

import com.github.cuzfrog.mytest.MyTestSuite

object Test1 extends MyTestSuite{
  test("this is my first test"){
    println("do some test1!")
  }

  test("this is a failed test"){
    throw new AssertionError("manual failure.")
  }
}
