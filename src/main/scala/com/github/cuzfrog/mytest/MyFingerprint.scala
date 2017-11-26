package com.github.cuzfrog.mytest

object MyFingerprint extends sbt.testing.AnnotatedFingerprint{
  override def isModule(): Boolean = true
  override def annotationName(): String = "com.github.cuzfrog.mytest.MyTest"
}
