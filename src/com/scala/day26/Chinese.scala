package com.scala.day26

/**
  * Created by Administrator on 2017/8/23.
  */
//trait 类似java中interface  extends可以继承trait 和 abstract
class Chinese extends Human with Animal with Monkey{
  //在Scala中重写一个非抽象的方法必须使用override修饰符
//  override def run(): Unit = {
//    println("run")
//  }
}

object Chinese{
  def main(args: Array[String]): Unit = {
    val c = new Chinese
    c.run()
  }
}
