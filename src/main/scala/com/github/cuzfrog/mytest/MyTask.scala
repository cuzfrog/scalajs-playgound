package com.github.cuzfrog.mytest

import org.scalajs.testinterface.TestUtils
import sbt.testing.{EventHandler, Logger, Task, TaskDef}

private class MyTask(override val taskDef: TaskDef,
                     testClassLoader: ClassLoader) extends sbt.testing.Task {
  override def tags(): Array[String] = Array("my-test-task")

  override def execute(eventHandler: EventHandler,
                       loggers: Array[Logger]): Array[Task] = {
    loggers.foreach(_.info(s"testing against: ${taskDef.fullyQualifiedName}"))
    val suite =
      TestUtils.loadModule(taskDef.fullyQualifiedName, testClassLoader).asInstanceOf[MyTestSuite]
    suite.run(eventHandler, loggers)(taskDef)
    Array.empty
  }

  override def execute(eventHandler: EventHandler,
                       loggers: Array[Logger],
                       continuation: Array[Task] => Unit): Unit = {
    this.execute(eventHandler, loggers)
    continuation(Array.empty)
  }
}
