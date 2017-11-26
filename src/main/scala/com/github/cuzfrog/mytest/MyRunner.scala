package com.github.cuzfrog.mytest

import sbt.testing.{Task, TaskDef}

private class MyRunner(override val args: Array[String],
                       override val remoteArgs: Array[String],
                       val testClassLoader: ClassLoader) extends sbt.testing.Runner {

  private var isDone: Boolean = false

  override def tasks(taskDefs: Array[TaskDef]): Array[Task] = taskDefs.map(new MyTask(_, testClassLoader))

  override def done(): String = {
    if (isDone) throw new IllegalStateException("The runner has already been executed.")
    else {
      this.isDone = true
      "my-test completed."
    }
  }

  override def receiveMessage(msg: String): Option[String] = {
    //no message sent back to slave
    None
  }

  override def serializeTask(task: Task, serializer: TaskDef => String): String = serializer(task.taskDef)

  override def deserializeTask(task: String, deserializer: String => TaskDef): Task = {
    new MyTask(deserializer(task), testClassLoader)
  }
}
