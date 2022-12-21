package com.days

import scala.io.Source

object DayFour extends App {
  val filename = "src/main/scala/com/days/files/input4.txt"
  val buffer = Source.fromFile(filename)
  val mList = buffer.getLines.toList
  val mListSeparateGroups = mList.map(elements => elements.split(",")).map {
    case Array(a, b) => (a, b)
  }

  def splitIntoTuple(ab: (String, String)): ((Int, Int), (Int, Int)) = ab match {
    case (a,b) => val splittedA = a.split("-")
                  val splittedB= b.split("-")
      (
        (splittedA(0).toInt,splittedA(1).toInt),(splittedB(0).toInt,splittedB(1).toInt)
      )
  }

  def isContained(tuple: ((Int, Int), (Int, Int))) : Int = tuple match {
    case (a, b) => if (a._1 <= b._1 && a._2 >= b._2) 1
              else if (b._1 <= a._1 && b._2 >= a._2) 1
              else 0
  }

  def overlaps(tuple: ((Int, Int), (Int, Int))): Int = tuple match {
    case (a, b) => if ( (Range.inclusive(a._1, a._2, 1) contains b._1) 
                     || (Range.inclusive(b._1, b._2, 1) contains a._1)) 1
              else if ( (Range.inclusive(a._1, a._2, 1) contains b._2)
                     || (Range.inclusive(b._1, b._2, 1) contains a._2)) 1
              else 0
  }


  val mListWithGroupAndTupleRange = mListSeparateGroups.map(splitIntoTuple)
  val isPlusGrand = mListWithGroupAndTupleRange.map(groupe => isContained(groupe))
  val isOverlapping = mListWithGroupAndTupleRange.map(groupe => overlaps(groupe))
  println(mListWithGroupAndTupleRange)
  println(isPlusGrand.sum)
  println(isOverlapping.sum)

}
