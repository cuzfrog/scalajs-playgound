package com.github.cuzfrog.mytest

import com.github.cuzfrog.nodejs.ChildProcess
import org.scalajs.testinterface.TestUtils
import sbt.testing._

import scala.concurrent.duration.Deadline
import scala.util.control.NonFatal

private class MyTask(override val taskDef: TaskDef,
                     testClassLoader: ClassLoader) extends sbt.testing.Task {
  override def tags(): Array[String] = Array("my-test-task")

  override def execute(eventHandler: EventHandler,
                       loggers: Array[Logger]): Array[Task] = {
    implicit val _taskDef: TaskDef = taskDef

    loggers.foreach(_.info(s"testing against: ${taskDef.fullyQualifiedName}"))
    val suite =
      TestUtils.loadModule(taskDef.fullyQualifiedName, testClassLoader).asInstanceOf[MyTestSuite]

    val jsTestCase = suite.extractTests(taskDef)
    JsTestConverter.generateJsTests(jsTestCase)

    val startTime = Deadline.now
    val event = try {
      ChildProcess.execSync(s"npm test -- ${jsTestCase.getPath}")
      //loggers.foreach(_.info(s"return: $result"))
      MyTestEvent(Status.Success)
    } catch {
      case NonFatal(t) =>
        loggers.foreach(_.error(s"test failed with $t}"))
        MyTestEvent(Status.Failure)
    }

    val duration = (Deadline.now - startTime).toMillis
    eventHandler.handle(event.copy(duration = duration): Event)

    Array.empty
  }

  override def execute(eventHandler: EventHandler,
                       loggers: Array[Logger],
                       continuation: Array[Task] => Unit): Unit = {
    this.execute(eventHandler, loggers)
    continuation(Array.empty)
  }
}
