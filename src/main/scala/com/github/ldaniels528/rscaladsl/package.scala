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
    * "as" object => as.integer(k)
    */
  object as extends AsType

  /**
    * "is" object => is.integer(k)
    */
  object is extends IsType

  /**
    * "read" object => read.csv(file = "./NASDAQ_20120927.txt", header = TRUE, sep = ",")
    */
  object read extends ReadFile

  /**
    * "to" object => to.data.frame = FALSE
    */
  object to {
    object data {
      var frame: RLogical = false
    }
  }

  ///////////////////////////////////////////////////////////////////
  //      Implicit conversions
  ///////////////////////////////////////////////////////////////////

  implicit def doubleToR(value: Double): RDouble = RDouble(value)

  implicit def intToR(value: Int): RInteger = RInteger(value)

  implicit def booleanToR(value: Boolean): RLogical = RLogical(value)

  implicit def rToBoolean(value: RLogical): Boolean = value.isTrue

  implicit def stringToR(value: String): RString = RString(value)

  /**
    * R-Variable Enrichment
    * @param a the host variable
    */
  implicit class RVariableEnrichment(val a: RVariable) extends AnyVal {

    def <=(b: RValue) = a.value = b

  }

}
