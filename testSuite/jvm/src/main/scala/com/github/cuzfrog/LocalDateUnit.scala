package com.github.cuzfrog

import java.time.LocalDate



/**
  * Created by cuz on 5/17/17.
  */
object LocalDateUnit {
  def dateParse(in: CharSequence): LocalDate = LocalDate.parse(in)
}