package com.github.cuzfrog.mytest

private class MyFingerprint extends sbt.testing.SubclassFingerprint {
  override def isModule(): Boolean = true
  override def superclassName(): String = "com.github.cuzfrog.mytest.MyTestSuite"
  override def requireNoArgConstructor(): Boolean = true
}
