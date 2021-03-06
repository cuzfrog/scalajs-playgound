package com.github.cuzfrog.mytest

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExportTopLevel
import scala.scalajs.reflect.Reflect

private object JsTestStub {
  @JSExportTopLevel("loadTest")
  def loadTest(fqcn: String, keyDescription: String): js.Function = {
    val testSuite = Reflect.lookupLoadableModuleClass(fqcn + "$") match {
      case Some(moduleClass) => moduleClass.loadModule().asInstanceOf[MyTestSuite]
      case None => throw new ClassNotFoundException(fqcn)
    }

    testSuite.jsTestCase.getTests.get(keyDescription) match {
      case Some(codeBlock) => codeBlock
      case None => throw new IllegalArgumentException(s"Cannot find test $keyDescription in $fqcn")
    }
  }

}
