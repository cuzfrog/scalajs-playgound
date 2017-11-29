package com.github.cuzfrog.mytest

import sbt.testing._

import scala.scalajs.js.annotation._
import scala.scalajs.reflect.annotation.EnableReflectiveInstantiation

private case class TestCase[T](description: String, codeBlock: () => T)

@EnableReflectiveInstantiation
abstract class MyTestSuite {
  private[mytest] val jsTestCase = new JsTestCase

  protected final def test[T](description: String)(block: => T): Unit = {
    jsTestCase.add(description, () => block)
  }

  private[mytest] final def extractTests(taskDef: TaskDef): JsTestCase = {
    jsTestCase.setName(taskDef.fullyQualifiedName())
  }

  @JSExport
  final def dummyTest(): Unit = println("dummyTest in MyTestSuite")
}
