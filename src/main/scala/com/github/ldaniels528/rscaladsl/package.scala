package com.github.ldaniels528

import scala.language.implicitConversions
import scala.util.Properties

/**
  * R-Scala DSL package object
  * @author Lawrence Daniels <lawrence.daniels@gmail.com>
  */
package object rscaladsl {
  val TRUE: RLogical = true
  val FALSE: RLogical = false

  type RCondition = RValue => Boolean

  ///////////////////////////////////////////////////////////////////
  //      Built-in functions
  ///////////////////////////////////////////////////////////////////

  def c(matrix: RMatrix) = matrix.data

  def c(values: RValue*) = RVector(values)

  def `class`(value: RValue) = value.`class`

  def getwd(): String = Properties.userDir

  def list(values: RValue*) = RList(values, headers = Nil)

  def matrix(data: RVector, nrow: RInteger, ncol: RInteger, byrow: RLogical) = RMatrix(data, nrow, ncol, byrow)

  def setwd(directory: String) = Properties.setProp("user.dir", directory)

  def subset(list: RListLike, conditions: RCondition*) = list match {
    case RList(items, headers) => RList(items = conditions.foldLeft(items)((list, cond) => list.filter(cond)), headers)
    case RDataFrame(items) => new RDataFrame(items = conditions.foldLeft(items)((list, cond) => list.filter(cond)))
    case RVector(items) => RVector(items = conditions.foldLeft(items)((list, cond) => list.filter(cond)))
    case _ => throw new IllegalArgumentException(s"Type '${list.`class`}' is not supported")
  }

  ///////////////////////////////////////////////////////////////////
  //      Built-in objects
  ///////////////////////////////////////////////////////////////////

  /**
    * "as" object
    * @example as.integer(k)
    */
  object as extends AsType

  /**
    * "data" object
    * @example data.frame(2:3, a="text", b=1)
    */
  object data extends DataType

  /**
    * "is" object
    * @example is.integer(k)
    */
  object is extends IsType

  /**
    * "read" object
    * @example read.csv(file = "./NASDAQ_20120927.txt", header = TRUE, sep = ",")
    */
  object read extends ReadFile

  /**
    * "to" object
    * @example to.data.frame = FALSE
    */
  object to extends ToType

  ///////////////////////////////////////////////////////////////////
  //      Implicit conversions
  ///////////////////////////////////////////////////////////////////

  implicit def doubleToR(value: Double): RDouble = RDouble(value)

  implicit def intToR(value: Int): RInteger = RInteger(value)

  implicit def booleanToR(value: Boolean): RLogical = RLogical(value)

  implicit def rToBoolean(value: RLogical): Boolean = value.isTrue

  implicit def stringToR(value: String): RString = RString(value)

  implicit def rToString(value: RString): String = value.value

  // range conversions

  implicit def rangeToR(range: Range): RVector = RVector(range.toVector.map(RInteger))

  // tuple conversions

  implicit def tupleBooleanToR(tuple: (String, Boolean)): RTuple = RTuple(tuple._1, tuple._2)

  implicit def tupleDoubleToR(tuple: (String, Double)): RTuple = RTuple(tuple._1, tuple._2)

  implicit def tupleIntToR(tuple: (String, Int)): RTuple = RTuple(tuple._1, tuple._2)

  implicit def tupleRValueToR(tuple: (String, RValue)): RTuple = RTuple(tuple._1, tuple._2)

  implicit def tupleStringToR(tuple: (String, String)): RTuple = RTuple(tuple._1, tuple._2)

  /**
    * R-Variable Enrichment
    * @param a the host variable
    */
  implicit class RVariableEnrichment(val a: RVariable) extends AnyVal {

    def <=(b: RValue) = a.value = b

  }

}
