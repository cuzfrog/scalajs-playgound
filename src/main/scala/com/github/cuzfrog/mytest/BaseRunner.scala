package com.github.cuzfrog.mytest
import sbt.testing.{Task, TaskDef}

private class MyRunner extends sbt.testing.Runner {
  override def tasks(taskDefs: Array[TaskDef]): Array[Task] = ???
  override def done(): String = ???
  override def remoteArgs(): Array[String] = ???
  override def args: Array[String] = ???
  override def receiveMessage(msg: String): Option[String] = ???
  override def serializeTask(task: Task, serializer: TaskDef => String): String = ???
  override def deserializeTask(task: String, deserializer: String => TaskDef): Task = ???
}
