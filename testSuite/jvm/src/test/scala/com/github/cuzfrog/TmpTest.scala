package com.github.cuzfrog

import java.time.LocalDate
import java.time.temporal.ChronoUnit

import scala.sys.process.Process

/**
  * Created by cuz on 5/17/17.
  */
object TmpTest extends App{
  Process("npm test").!
}
