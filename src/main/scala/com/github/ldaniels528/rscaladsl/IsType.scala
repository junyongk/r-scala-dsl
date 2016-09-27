package com.github.ldaniels528.rscaladsl

/**
  * R's "is" object
  * @author Lawrence Daniels <lawrence.daniels@gmail.com>
  */
class IsType {

  def double(value: RValue): RLogical = value.isInstanceOf[RDouble]

  def integer(value: RValue): RLogical = value.isInstanceOf[RInteger]

  def logical(value: RValue): RLogical = value.isInstanceOf[RLogical]

  def numeric(value: RValue): RLogical = value.isInstanceOf[RNumeric]

}
