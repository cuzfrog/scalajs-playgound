package com.github.cuzfrog

import scala.scalajs.js
import scala.scalajs.js.JSON

object TmpApp {
  def main(args: Array[String]): Unit = {

    val test = new MyJsObject
    val serialized0 = JSON.stringify(test)
    println(serialized0)

    test.act()
    val serialized = JSON.stringify(test)

    println(serialized)
    val deserialized = JSON.parse(serialized).asInstanceOf[MyJsObject]

    deserialized.act()
  }
}

class MyJsObject extends js.Object {
  private var value = 0

  def act(): Unit = {
    value += 1
    println(value)
  }
}