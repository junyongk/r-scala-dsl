package com.github.ldaniels.rscaladsl.examples

import com.github.ldaniels528.rscaladsl._

import scala.language.postfixOps

/**
  * R-Scala DSL Examples
  * @author Lawrence Daniels <lawrence.daniels@gmail.com>
  */
object ScalaToRTest {

  def main(args: Array[String]) = example4()

  def example1() = {
    val x: RValue = 10.5 // assign a decimal value
    println(x) // print the value of x
    println(`class`(x))

    println(is.integer(x))
    println(is.integer(10))
    println(as.integer("12345"))
  }

  def example2() = {
    val data = RVariable()

    data <= read.csv(file = "./NASDAQ_20120927.txt", header = TRUE, sep = ",")
    println(data)
  }

  def example3() = {
    println(c(1, 2, 3, 4, 5))
  }

  def example4() = {
    val A = matrix(
      c(2, 4, 3, 1, 5, 7), //the data elements
      nrow = 2, // number of rows
      ncol = 3, // number of columns
      byrow = TRUE) // fill matrix by rows
    println(A)
  }


}