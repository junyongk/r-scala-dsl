package com.github.ldaniels528.rscaladsl

/**
  * R-Table Generator
  * @author lawrence.daniels@gmail.com
  */
object TableGenerator {

  /**
    * Transforms the given sequence of objects into a sequence of string that
    * represent a table.
    */
  def transform[A](headers: Seq[String], values: Seq[Seq[A]]): Seq[String] = {
    if (values.isEmpty) Nil
    else {
      // get the headers, data rows, and column widths
      val rows = convert(headers, values)

      // create the table
      makeTable(headers, rows)
    }
  }

  private def makeTable(headers: Seq[String], rows: Seq[Map[String, String]]): List[String] = {
    // create the horizontal border, header and compute column widths
    val widths = columnWidths(headers, rows)
    val borderLine = s"+ ${"-" * widths.sum} +"
    val headerLine = s"| ${constructRow(headers zip widths)} |"

    // create the data grid
    val dataGrid = (rows map { row =>
      val data = headers map (row.getOrElse(_, " "))
      s"| ${constructRow(data zip widths)} |"
    }).toList

    // create the table
    borderLine :: headerLine :: borderLine :: dataGrid ::: borderLine :: Nil
  }

  private def convert[A](headers: Seq[String], values: Seq[Seq[A]]) = {
    values map { row =>
      Map(headers zip row.map(asString): _*)
    }
  }

  private def asString(value: Any): String = {
    import java.text.SimpleDateFormat
    import java.util.Date

    val output = value match {
      case v if v == null => ""
      case d: Date => new SimpleDateFormat("MM/dd/yy hh:mm:ss z").format(d)
      case o: Option[_] => if (o.isDefined) asString(o.get) else ""
      case v => String.valueOf(v)
    }
    if (output.length > 140) output.substring(0, 140) + "..." else output
  }

  private def constructRow(values: Seq[(String, Int)]): String = {
    (values map { case (data, width) => data + " " * Math.abs(width - data.length) }).mkString
  }

  /**
    * Computes the width of each column
    */
  private def columnWidths(headers: Seq[String], rows: Seq[Map[String, String]]) = {
    import java.lang.Math.max

    // define a function to compute the maximum length of the key-value pair
    def smash(k: String, v: String, currentMax: Int) = max(max(k.length, v.length), currentMax)

    // reduce the rows to a mapping of column to max width
    val result = rows.foldLeft[Map[String, Int]](Map.empty) {
      (res, row) =>
        res ++ (row map { case (k, v) => (k, smash(k, v, res.get(k) map (max(k.length, _)) getOrElse k.length)) })
    }

    // return just the column widths in the appropriate order
    headers map (result(_) + 2)
  }

}
