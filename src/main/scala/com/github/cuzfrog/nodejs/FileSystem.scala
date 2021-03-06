package com.github.cuzfrog.nodejs

import scala.annotation.tailrec
import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|

@js.native
@JSImport("fs", JSImport.Namespace)
private object FileSystem extends js.Object {
  def openSync(file: String, flags: String | Int,
               mode: js.UndefOr[Int] = js.undefined): Int = js.native

  def closeSync(fd: Int): Unit = js.native

  def existsSync(file: String): Boolean = js.native

  def mkdirSync(file: String): Unit = js.native

  def writeFileSync(file: String | Int, data: String,
                    options: js.UndefOr[js.Object] = js.undefined): Unit = js.native
}

@js.native
@JSImport("path", JSImport.Namespace)
private object Path extends js.Object {
  def dirname(file: String): String = js.native
}

object FSUtils {
  def write(file: String, content: String): Unit = {
    ensureDirectoryExistence(file)
    FileSystem.writeFileSync(file, content)
  }

  private def ensureDirectoryExistence(file: String) {
    var dirname = Path.dirname(file)
    if (!FileSystem.existsSync(dirname)) {
      ensureDirectoryExistence(dirname)
      FileSystem.mkdirSync(dirname)
    }
  }
}