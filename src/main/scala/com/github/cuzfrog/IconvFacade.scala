package com.github.cuzfrog

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native
@JSImport("iconv-lite", JSImport.Namespace)
object IconvFacade extends js.Any {
  def decode(buffer: js.Object, encoding: String): String = js.native
  def encode(in: String, encoding: String): js.Object = js.native
}
