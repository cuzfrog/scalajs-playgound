package com.github.cuzfrog

import scala.scalajs.js
import com.github.cuzfrog.nodejs.ChildProcess

object TmpApp {
  def main(args: Array[String]): Unit = {
    val output = ChildProcess.execSync("npm test")
    println(s"Exit code: $output")
  }
}
