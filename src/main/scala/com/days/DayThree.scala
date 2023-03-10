package com.days

import scala.io.Source

object DayThree extends App{
  val filename = "src/main/scala/com/days/files/input3.txt"
  val buffer = Source.fromFile(filename)
  val mList = buffer.getLines.toList
  val rucksacksPockets = mList.map(lignes => lignes.splitAt(lignes.length/2))
  val arrayOfChar: (String) => Array[Char] = (a => a.toCharArray)

  def compareRuckSacks(ab: (String, String)): Array[Char] = ab match {
    case (a, b) => arrayOfChar(a).intersect(arrayOfChar(b))
  }
  def findMostPresentLetter(l:List[String]):Char = l match {
    case(List(a,b,c)) => arrayOfChar(a).intersect(arrayOfChar(b).intersect(arrayOfChar(c)))(0) // un seul élément similaire entre le tuple
  }
  def charToCorrectValue(toDetermine: Char):Int = {
    if(toDetermine>=97) toDetermine-96 else toDetermine-65+27
  }

  // Always only 1 char is returned by compareRuckSacks -> keep only the first char
  val res = rucksacksPockets.map(tuple => compareRuckSacks(tuple)(0))
  // if lowercase -> goes from 1 to 26 else -> goes from 27 to 52
  val valueRes = res.map(item => if(item>=97) item-96 else item-65+27)

  // Part Two
  val rucksacksGroup = mList.grouped(3).toList
  val resBadges = rucksacksGroup.map(tripleList => findMostPresentLetter(tripleList))
  println(valueRes.sum)
  print(resBadges.map(item => charToCorrectValue(item)).sum)
}
