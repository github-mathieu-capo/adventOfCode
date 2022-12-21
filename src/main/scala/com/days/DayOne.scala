package com.days

import scala.io.Source

object DayOne extends App {

  val filename = "src/main/scala/com/days/files/input.txt"
  val buffer = Source.fromFile(filename)
  val mList = buffer.getLines.toList

  def splitListBySeparator(l:List[String], sep :String) : List[List[String]] = {
    l.span( _ != sep) match {
      case(head, _ :: tail) => head:: splitListBySeparator(tail, sep)
      case (head, _) => List(head)
    }
  }

  val splittedList = splitListBySeparator(mList, "") // mList = List(1,2,3, ,4,5, ,6), separate when empty
  val mListToInt = splittedList.map(lists => lists.map(_.toInt)) // splittedList = List(List(1,2,3),List(4,5),List(6))
  val mSumList = mListToInt.map(lists=>lists.sum) // mSumList = List(6,9,6)
  val top1 = mSumList.max
  val top2 = mSumList.filter(_ != top1).max // keep everything different from top1 and take the max
  val top3 = mSumList.filter(_ < top2).max // keep everything lower than top 2 and take the max
  val tot = top1+top2+top3
  println(s"top 1 : $top1 top 2 : $top2 top 3: $top3 tot = $tot")
  buffer.close
}
