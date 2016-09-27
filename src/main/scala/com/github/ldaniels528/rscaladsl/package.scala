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

  ///////////////////////////////////////////////////////////////////
  //      Built-in functions
  ///////////////////////////////////////////////////////////////////

  def c(matrix: RMatrix) = matrix.data

  def c(values: RValue*) = RVector(values)

  def `class`(value: RValue) = value.`class`

  def getwd(): String = Properties.userDir

  def list(values: RValue*) = RList(headers = Nil, Seq(values))

  def matrix(data: RVector, nrow: RInteger, ncol: RInteger, byrow: RLogical) = RMatrix(data, nrow, ncol, byrow)

  def setwd(directory: String) = Properties.setProp("user.dir", directory)

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

  implicit def tupleToIntR(tuple: (String, Int)): RTuple = RTuple(tuple._1, tuple._2)

  implicit def tupleToStringR(tuple: (String, String)): RTuple = RTuple(tuple._1, tuple._2)

  /**
    * R-Variable Enrichment
    * @param a the host variable
    */
  implicit class RVariableEnrichment(val a: RVariable) extends AnyVal {

    def <=(b: RValue) = a.value = b

  }

}
