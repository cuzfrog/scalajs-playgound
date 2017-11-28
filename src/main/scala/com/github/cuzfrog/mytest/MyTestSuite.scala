package com.github.cuzfrog.mytest

import com.github.cuzfrog.nodejs.ChildProcess
import sbt.testing._

import scala.collection.mutable.ArrayBuffer
import scala.concurrent.duration.Deadline
import scala.scalajs.reflect.annotation.EnableReflectiveInstantiation
import scala.util.control.NonFatal

private case class TestCase[T](description: String, codeBlock: () => T)

@EnableReflectiveInstantiation
abstract class MyTestSuite {
  private[this] val tests: ArrayBuffer[TestCase[_]] = ArrayBuffer.empty

  protected final def test[T](description: String)(block: => T): Unit = {
    tests += TestCase(description, () => block)
  }

  private[mytest] final def extractTests(taskDef: TaskDef): JsTestCase = {
    val testStub = new JsTestCase(taskDef.fullyQualifiedName())
    tests.foreach { testCase =>
      testStub.add(testCase.description, testCase.codeBlock)
    }
    testStub
  }
}
