package com.days

import scala.io.Source

object DayTwo extends App {
  val filename = "src/main/scala/com/days/files/input2.txt"
  val buffer = Source.fromFile(filename)
  val mList = buffer.getLines.toList
  val mListSeparateMove = mList.map(elements => elements.split(" ").toList)
  val valuedData = mListSeparateMove.map(lists => lists match {
    case List("A", "X") => 3+0 // 3 = scissors 0 = lose(0)
    case List("A", "Y") => 1+3 // 1 = rock Y = draw (3)
    case List("A", "Z") => 2+6 // 2 = paper Z = win (6)
    case List("B", "X") => 1+0
    case List("B", "Y") => 2+3
    case List("B", "Z") => 3+6
    case List("C", "X") => 2+0
    case List("C", "Y") => 3+3
    case List("C", "Z") => 1+6
  })
  print(valuedData.sum)
}
