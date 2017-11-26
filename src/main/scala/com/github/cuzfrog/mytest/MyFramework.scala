package com.github.cuzfrog.mytest

import sbt.testing.{Fingerprint, Runner}

class MyFramework extends sbt.testing.Framework {
  override final def name(): String = "my-test"

  override final def fingerprints(): Array[Fingerprint] = Array(new MyFingerprint)

  override final def runner(args: Array[String],
                            remoteArgs: Array[String],
                            testClassLoader: ClassLoader): Runner = {
    new MyRunner(args, remoteArgs, testClassLoader)
  }

  override final def slaveRunner(args: Array[String],
                                 remoteArgs: Array[String],
                                 testClassLoader: ClassLoader,
                                 send: String => Unit): Runner = {
    this.runner(args, remoteArgs, testClassLoader)
  }
}
