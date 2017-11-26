package com.github.cuzfrog.mytest

import sbt.testing._

private case class MyTestEvent private(override val fullyQualifiedName: String,
                                       override val fingerprint: Fingerprint,
                                       override val selector: Selector,
                                       override val status: Status,
                                       override val throwable: OptionalThrowable,
                                       override val duration: Long
                                      ) extends sbt.testing.Event with Product with Serializable

private object MyTestEvent {
  def apply(status: Status,
            duration: Long = -1,
            throwable: OptionalThrowable = new OptionalThrowable())
           (implicit taskDef: TaskDef): MyTestEvent = {
    new MyTestEvent(
      taskDef.fullyQualifiedName(),
      taskDef.fingerprint(),
      taskDef.selectors().head,
      status,
      throwable,
      duration
    )
  }
}