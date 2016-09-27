package com.github.ldaniels528.rscaladsl

import com.github.ldaniels528.rscaladsl.ToType.Data

/**
  * The "to" definition
  * @author Lawrence Daniels <lawrence.daniels@gmail.com>
  */
class ToType {
  val data = Data(frame = false)
}

object ToType {

  case class Data(var frame: RLogical)

}
