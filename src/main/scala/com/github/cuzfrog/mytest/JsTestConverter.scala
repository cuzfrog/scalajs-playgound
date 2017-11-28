package com.github.cuzfrog.mytest

import com.github.cuzfrog.nodejs.{FSUtils, FileSystem}

import scala.collection.mutable
import scala.scalajs.js
import scala.scalajs.js.annotation._

private object JsTestConverter {
  def generateJsTests(testStub: JsTestCase): Unit = {
    val module = "const testStub = require('../../js-tests/target/scala-2.12/js-tests-test-fastopt.js').TestStub;"
    val contents = testStub.tests.map { case (description, _) =>
      s"""test('$description', () => {
         |  testStub.test.tests.get('$description')();
         |});
         |
      """.stripMargin
    }
    val NEWLINE = System.lineSeparator()
    val content = module + NEWLINE + contents.mkString

    FSUtils.write(testStub.path, content)
  }
}

@JSExportTopLevel("JsTestCase")
final class JsTestCase(val name: String) {
  require(name.matches("""[\w-_\.]*"""), s"Bad module name: $name")

  val path: String = s"target/my-tests/$name.test.js"

  @JSExport
  val tests: mutable.Map[String, js.Function] = mutable.Map.empty

  def add[T](description: String, testBlock: () => T): Unit = {
    tests += (description -> testBlock)
  }
}

@JSExportTopLevel("TestStub")
object TestStub {
  var test: JsTestCase = _

  def set(jsTestCase: JsTestCase): Unit = {
    test = jsTestCase
  }
}