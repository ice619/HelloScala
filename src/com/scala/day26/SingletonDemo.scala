package com.scala.day26

/**
  * Created by Administrator on 2017/8/22.
  */
//scala中说对象一般指object
object SingletonDemo {
  def main(args: Array[String]): Unit = {
    val s = SingletonDemo
    println(s)
    val b = SingletonDemo
    println(b)
  }
}
