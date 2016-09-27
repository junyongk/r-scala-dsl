package com.github.ldaniels528.rscaladsl

/**
  * R's "data" object
  * @author Lawrence Daniels <lawrence.daniels@gmail.com>
  */
class DataType {

  def frame(items: RValue*) = DataFrame(items)

}
