package com.github.ldaniels528.rscaladsl

import scala.io.Source

/**
  * File Reader Abstraction
  * @author Lawrence Daniels <lawrence.daniels@gmail.com>
  */
class ReadFile {

  /**
    * Reads a CSV-formatted file
    * @example read.csv(file = "./NASDAQ_20120927.txt", header = TRUE, sep = ",")
    */
  def csv(file: String, header: RLogical = false, sep: String = null) = {
    val lines = Source.fromFile(file).getLines()
    val headers = (if (header) Some(lines.next()) else None).map(divide(_, sep)) getOrElse Seq("NA")
    val items = lines.map(line => RList(items = divide(line, sep).map(RString))).toSeq
    RList(items, headers)
  }

  /**
    * Reads a MTP-formatted file
    * @example read.mtp("mydata.mtp")
    */
  def mtp(file: String) = {
    throw new IllegalStateException("MTP is not yet implemented")
  }

  /**
    * Reads a SPSS-formatted file
    * @example read.spss("myfile", to.data.frame=TRUE)
    */
  def spss(file: String, to_data_frame: RLogical) = {
    throw new IllegalStateException("SPSS is not yet implemented")
  }

  /**
    * Reads a table from a file
    * @example table("mydata.txt")
    */
  def table(file: String) = {
    throw new IllegalStateException("Table is not yet implemented")
  }

  /**
    * Divides the line into a sequence of string by the given separator
    */
  private def divide(line: String, sep: String) = {
    if (sep != null) line.split(s"[$sep]").toSeq else Seq(line)
  }

}
