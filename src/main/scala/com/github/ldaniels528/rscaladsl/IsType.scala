package com.github.ldaniels528.rscaladsl

import com.github.ldaniels528.rscaladsl.IsType.IsDataType

/**
  * R's "is" object
  * @author Lawrence Daniels <lawrence.daniels@gmail.com>
  */
class IsType {

  object data extends IsDataType

  def double(value: RValue): RLogical = value.isInstanceOf[RDouble]

  def integer(value: RValue): RLogical = value.isInstanceOf[RInteger]

  def list(value: RValue): RLogical = value.isInstanceOf[RList]

  def logical(value: RValue): RLogical = value.isInstanceOf[RLogical]

  def numeric(value: RValue): RLogical = value.isInstanceOf[RNumeric]

  def vector(value: RValue): RLogical = value.isInstanceOf[RVector]

}

/**
  * IsType Companion
  *  @author Lawrence Daniels <lawrence.daniels@gmail.com>
  */
object IsType {

  class IsDataType {

    def frame(value: RValue): RLogical = value.isInstanceOf[RDataFrame]

  }


}