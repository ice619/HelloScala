package com.scala.day26

/**
  * Created by Administrator on 2017/8/22.
  */
class Dog {

}
object Dog{
//  def apply(): Unit ={
//    println("apply")
//  }
  def apply(): Dog ={
    println("apply")
    new Dog
  }
  def apply(name: String): Unit = {
    println(name)
  }

  def main(args: Array[String]): Unit = {
    val d = Dog("aa")//要带括号才会调用apply方法
    println(d)
    val d1 = Dog()
    println(d1)
  }

}
