package com.github.cuzfrog

import java.time.LocalDate
import java.time.temporal.ChronoUnit

/**
  * Created by cuz on 5/17/17.
  */
object TmpTest extends App{
  val first = LocalDate.of(2017,5,1)
  val last = LocalDate.of(2017,5,2)
  val span=ChronoUnit.MONTHS.between(first, last)
  println(span)
}
