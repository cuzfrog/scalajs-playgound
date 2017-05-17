package com.github.cuzfrog

import java.time.LocalDate

import utest._


object DateParseTest extends TestSuite {
  import LocalDateUnit.dateParse

  val tests = this {
    'positive {
      assert(dateParse("2017-05-01") == LocalDate.of(2017, 5, 1))
      assert(dateParse("1924-12-31") == LocalDate.of(1924, 12, 31))
    }
    'problematic{
      dateParse("2001-1-1")
    }
  }
}

