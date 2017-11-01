package com.scala.day26

/**
  * Created by Administrator on 2017/8/23.
  */
class Cat {//主构造器
  var name : String = "aa"
  def this(name : String){//辅助构造器，构造器重载
    this()//第一行辅构造器要先调用主构造器
    this.name = name
  }

}
