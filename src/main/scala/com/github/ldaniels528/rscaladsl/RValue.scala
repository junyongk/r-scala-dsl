package com.github.ldaniels528.rscaladsl

/**
  * Represents an R-value
  * @author Lawrence Daniels <lawrence.daniels@gmail.com>
  */
sealed trait RValue {
  def `class`: String
}

/**
  * Represents a data frame
  */
class RDataFrame(val items: Seq[RValue]) extends RListLike {
  override val `class` = "frame"

  override def toString = items.mkString(", ")
}

/**
  * RDataFrame Companion
  */
object RDataFrame {

  def apply(items: RValue*) = new RDataFrame(items)

  def unapply(frame: RDataFrame): Option[Seq[RValue]] = Option(frame.items)

}

/**
  * Represents an R-Double value
  */
case class RDouble(value: Double) extends RNumeric {
  override val `class` = "numeric"

  override def toString = value.toString
}

/**
  * Represents an R-Integer value
  */
case class RInteger(value: Int) extends RNumeric {
  override val `class` = "numeric"

  override def toString = value.toString
}

/**
  * R-List Collection
  */
case class RList(items: Seq[RValue], headers: Seq[String] = Nil) extends RListLike {
  override val `class` = "list"

  override def toString = {
    val values = items map {
      case RList(theItems, _) => theItems.map(_.toString)
      case v => Seq(v.toString)
    }
    TableGenerator.transform(headers, values) mkString "\n"
  }
}

/**
  * R List-like Collection
  */
trait RListLike extends RValue {

  def items: Seq[RValue]

}

case class RLogical(value: Boolean) extends RValue {
  def isTrue = value

  override val `class` = "logic"

  override def toString = if (value) "TRUE" else "FALSE"
}

/**
  * Represents a R-compatible Null value
  */
object RNull extends RValue {
  override val `class` = "NA"

  override def toString = "NA"
}

case class RMatrix(data: RVector, nrow: RInteger, ncol: RInteger, byrow: RLogical) extends RValue {
  override val `class` = "matrix"

  override def toString = data.toString
}

/**
  * Represents an R Numeric value
  */
trait RNumeric extends RValue

/**
  * Represents an R Numeric value
  */
case class RString(value: String) extends RValue {
  override val `class` = "string"

  override def toString = value
}

/**
  * Represents an R Tuple; a name-value pair.
  */
case class RTuple(name: String, value: RValue) extends RValue {
  override val `class` = value.`class`

  override def toString = s"$name=$value"
}

/**
  * Represents an R variable
  */
case class RVariable(var value: RValue = RNull) extends RValue {
  override val `class` = value.`class`

  override def toString = value.toString
}

/**
  * R-like Vector
  */
case class RVector(items: Seq[RValue]) extends RListLike {
  override val `class` = "vector"

  override def toString = items.mkString(", ")
}