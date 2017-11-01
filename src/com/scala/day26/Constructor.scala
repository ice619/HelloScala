package com.scala.day26

import scala.io.Source

/**
  * Created by Administrator on 2017/8/22.
  */
class Constructor {
  //主构造器会执行类定义中所有语句，其中方法只是会加载，不会被执行，
  var name = "aa"
  println(name)

  def aa ={
    println("aa")
  }

  try {
    val content = Source.fromFile("d://test.py").mkString
    println(content)
  } catch {
    case e: Exception => e.printStackTrace()
  } finally {
    println("finally")
  }

}
object Constructor{
  def main(args: Array[String]): Unit = {
    val c = new Constructor
  }
}
