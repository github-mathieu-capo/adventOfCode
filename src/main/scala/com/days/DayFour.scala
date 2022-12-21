package com.days

import scala.io.Source

object DayFour extends App {
  val filename = "src/main/scala/com/days/files/input4.txt"
  val buffer = Source.fromFile(filename)
  val mList = buffer.getLines.toList
  val mListSeparateGroups = mList.map(elements => elements.split(",")).map {
    case Array(a, b) => (a, b)
  }

  def splitIntoTuple(ab: (String, String)): ((String,String),(String,String)) = ab match {
    case (a,b) => val splittedA = a.split("-")
                  val splittedB= b.split("-")
      (
        (splittedA(0),splittedA(1)),(splittedB(0),splittedB(1))
      )
  }

  def isContained(tuple: ((String, String), (String, String))) : Int = tuple match {
    case (a,b) =>  if(a._1 <= b._1 && a._2 >= b._2) 1
              else if(b._1 <= a._1 && b._2 >= a._2) 1
              else 0
  }
  val mListWithGroupAndTupleRange = mListSeparateGroups.map(splitIntoTuple)
  val isPlusGrand = mListWithGroupAndTupleRange.map(groupe => isContained(groupe))
  println(mListWithGroupAndTupleRange)
  println(isPlusGrand.sum)

}
