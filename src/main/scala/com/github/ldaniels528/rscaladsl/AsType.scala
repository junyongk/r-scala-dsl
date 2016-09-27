package com.github.ldaniels528.rscaladsl

import com.github.ldaniels528.rscaladsl.AsType.AsDataType

import scala.util.Try

/**
  * R's "as" object
  * @author Lawrence Daniels <lawrence.daniels@gmail.com>
  */
class AsType {

  object data extends AsDataType

  def double(rvalue: RValue): RValue = {
    rvalue match {
      case RDouble(v) => RDouble(v)
      case RInteger(v) => RDouble(v)
      case RString(s) => Try(RDouble(s.toDouble)).toOption.getOrElse(RNull)
      case _ => RNull
    }
  }

  def integer(rvalue: RValue): RValue = {
    rvalue match {
      case RDouble(v) => RInteger(v.toInt)
      case RInteger(v) => RInteger(v)
      case RString(s) => Try(RInteger(s.toInt)).toOption.getOrElse(RNull)
      case _ => RNull
    }
  }

  def logical(value: RValue): RLogical = {
    value match {
      case RInteger(n) => n > 0
      case RDouble(n) => n > 0
      case RNull => false
      case _ => true
    }
  }

}

/**
  * AsType Companion
  *  @author Lawrence Daniels <lawrence.daniels@gmail.com>
  */
object AsType {

  class AsDataType {

    def frame(value: RValue): RDataFrame = ???

  }


}