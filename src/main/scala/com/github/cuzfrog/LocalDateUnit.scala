package com.github.cuzfrog

import java.time.LocalDate
import java.time.format.DateTimeParseException

import scala.scalajs.js

/**
  * Created by cuz on 5/17/17.
  */
object LocalDateUnit {
  def dateParse(in: CharSequence): LocalDate = {
    if(!in.matches("""\d{4}-\d{2}-\d{2}""")) throw new DateTimeParseException()

    val date = new js.Date(js.Date.parse(in.toString))
    val year = date.getUTCFullYear
    val month = date.getUTCMonth + 1 //months from 1-12
    val day = date.getUTCDate
    LocalDate.of(year, month, day)
  }
}