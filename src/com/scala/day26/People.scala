package com.scala.day26

/**
  * Created by Administrator on 2017/8/22.
  */
//主构造器    gender: String: private[this]
// 如果是val age: Int = 18  在构造器中还可以改，只是在外面不能改了
class People(val id: String, var name: String, gender: String, var age: Int = 18){

}
object People{
  def main(args: Array[String]): Unit = {
    val p = new People("123", "xiaoting", "f")
    println(p.id + p.name)
    p.name = "xueting"
    println(p.id + p.name)
  }
}
