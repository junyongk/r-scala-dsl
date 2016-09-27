package com.github.ldaniels528.rscaladsl

/**
  * Represents an R-value
  * @author Lawrence Daniels <lawrence.daniels@gmail.com>
  */
sealed trait RValue {
  def `class`: String
}

/**
  * Represents an R-Double value
  * @author Lawrence Daniels <lawrence.daniels@gmail.com>
  */
case class RDouble(value: Double) extends RNumeric {
  override val `class`: String = "numeric"

  override def toString = value.toString
}

/**
  * Represents an R-Integer value
  * @author Lawrence Daniels <lawrence.daniels@gmail.com>
  */
case class RInteger(value: Int) extends RNumeric {
  override val `class`: String = "numeric"

  override def toString = value.toString
}

/**
  * R-List Collection
  * @author Lawrence Daniels <lawrence.daniels@gmail.com>
  */
case class RList[T](headers: Seq[String], items: Seq[Seq[T]]) extends RValue {
  override val `class`: String = "list"

  override def toString = TableGenerator.transform(headers, items).mkString("\n")
}

case class RLogical(value: Boolean) extends RValue {
  def isTrue = value

  override val `class`: String = "logic"

  override def toString = if (value) "TRUE" else "FALSE"
}

/**
  * Represents a R-compatible Null value
  * @author Lawrence Daniels <lawrence.daniels@gmail.com>
  */
object RNull extends RValue {
  override val `class`: String = "NA"

  override def toString = "NA"
}

case class RMatrix(data: RVector, nrow: RInteger, ncol: RInteger, byrow: RLogical) extends RValue {
  override val `class`: String = "matrix"

  override def toString = data.toString
}

/**
  * Represents an R Numeric value
  * @author Lawrence Daniels <lawrence.daniels@gmail.com>
  */
trait RNumeric extends RValue

/**
  * Represents an R Numeric value
  * @author Lawrence Daniels <lawrence.daniels@gmail.com>
  */
case class RString(value: String) extends RValue {
  override val `class`: String = "string"

  override def toString = value
}

/**
  * Represents an R variable
  * @author Lawrence Daniels <lawrence.daniels@gmail.com>
  */
case class RVariable(var value: RValue = RNull) extends RValue {
  override val `class`: String = value.`class`

  override def toString = value.toString
}

/**
  * R-like Vector
  * @author Lawrence Daniels <lawrence.daniels@gmail.com>
  */
case class RVector(values: Seq[RValue]) extends RValue {
  override val `class`: String = "vector"

  override def toString = values.mkString(", ")
}