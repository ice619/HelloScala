package com.scala.day25
import java.util

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.Map;
/**
  * Created by Administrator on 2017/8/16.
  */
object HelloScala {
  val func = (x: Int) => x * 10
  def m1(f: Int => Int): Int ={  //f: Int => Int  类比 x: Int  只不过f是 Int => Int 函数类型
    f(3)//在方法里调用函数
  }

  def main(args: Array[String]): Unit = {

//    val r = m1(func)
//    println(r)
//    println("hello scala")
//    val a = for(i <- 1 to 10) yield i * 10 //yield 会新生成一个数组
//    println(a)
//    val b = 1.to(10).map(_ * 100)
//    println(b)

    val arr = Array(1,2,3,4,5,6,7,8,9)
    val r1 = arr.map(x => x * 5)
    val r2 = arr.map(x => x -1)
    println(r1.toBuffer)//buffer中重写了toString方法，可以打印出内容
    println(r2.toBuffer)
    println(arr.toBuffer)

    //定义方法
    def m2(x: Int, y: Int) : Int = x * y
    def m1(x: Int, y: Int) : Int = {x * y}
    def m3(x: String): Unit ={    //Unit相当于java中的void
      println(x)
    }
    def m4(x: String){
      println(x)
    }
    //定义函数俩种方式
    //(x: Int) => x.toString  匿名函数
    val func0 = (x: Int) => x.toString
    val func: Int => String = {x => x.toString}
    // Int 函数传参类型，String 函数返回值类型（也可以是个元组）  x  传入的参数引用

    val func1 = (s: Int, y: Double) => (y, s)
    val func2: (Int, Double) => (Double, Int) = { (a, b) => (b, a)}
    println(func(10))

    //神奇的下划线，可以将方法转换成函数
    def m5(x: Int, y: Int): Int = x + y
    val f5 = m5 _

    //数组
    val arr3 = new Array[Int](10)//Int是范型   arr(0)
    val arr2 = Array[Int](10)
    val arr1 = Array(1,2,3,4,5,6)
    //可变长度数组
    val ab = new ArrayBuffer[Int]()
    ab += 1
    ab ++= Array(456)
    ab.insert(1,2,3)
    //ab.remove(1,2)
    println(ab)
    //遍历数组
    for(i <- ab) println(i)
    for(i <- (0 until ab.length).reverse) println(ab(i))
    ab.map(println(_))//map()拿出来每个元素进行操作
    //yeild 产生个新的
    val a = Array(1,2,3,4,5,6)
    for(i <- a) yield i * 10
    a.map((x: Int) => x * 10)
    a.map(x => x * 10)
    a.map(_ * 10)
    //取偶数*10
    a.filter((x : Int) => x % 2 == 0)
    a.filter(x => x %2 == 0)
    a.filter(_ %2 == 0)
    a.filter(_ %2 == 0).map(_ * 10)
    //
    a.sum
    a.sorted
    a.sorted.reverse
    a.sortBy(x => x)
    a.sortWith(_>_)
    a.sortWith(_<_)
    a.sortWith((x, y) => x > y)
    a.sortWith((x, y) => x < y)

    //映射 java中叫map
    val m = Map("a" -> 1, "b" -> 2)//内容不可变
    val mn = Map(("a",1),("b",2))
    //import scala.collection.mutable.Map; 可变
    val mm = Map("i" -> 1, "j" -> 2)
    mm("i") = 3
    mm("o") = 3
    mm += ("c" -> 7)
    mm += (("c",7))
    mm += (("c",6))
    println(mm)
    mm.getOrElse("a", 5)//要是没有就给个默认值

    val hm = new util.HashMap()//可以调用java类

    //元组
    val t = (1, "spark", 2.0)
    t._2//元组下角标从1开始
    val pair = ("t", 5)
    mm += pair//对偶元组可以追加到
    //mm += ("y", 6)//错误
    mm += (("y",7))//表明是元组
    mm += (("y",7),("c",8))

    val tt,(x, y, z) = ("a",1,2.0)
    println(x)

    //将对偶的集合转换成映射
    val arr6 = Array(("a", 1),("b", 2))
    arr6.toMap

    //Scala的集合有三大类：序列Seq、集Set、映射Map，
    val list1 = List(1,2,3)
    val list2 = 0 :: list1 // :: 操作符是将给定的头和尾创建一个新的列表 右结合
    val list3 = list1.::(0)
    val list4 = 0 +: list1
    val list5 = list1 :+ 0
    val list6 = list1. +: (0)

    //wordCount 单词计数
    val lines = List("hello tom hello aa","hello tom hello kitty")
    lines.map(_.split(" ")).flatten
    val words = lines.flatMap(_.split(" ")) //flatMap  先Map再压平
    //map里是一个一个元组,map方法会将返回值放入一个新的数组或者集合 foreach不会，arr.foreach(x => println(x))
    val wordsAndOne = words.map((_, 1))
    val grouped = wordsAndOne.groupBy(_._1)//得到Map，里面是一个一个的对偶元组，map
//    val result = grouped.map(_._1, _._2.size)//错误的
    val result = grouped.map(t => (t._1, t._2.size))//map里相当于一个匿名函数
    val finalResult = result.toList.sortBy(_._2)
    val finalResult2 = result.toList.sortBy(_._2).reverse
    println(finalResult)

    val finalResult3 = lines.flatMap(_.split(" ")).map((_, 1)).groupBy(_._1).map(t => (t._1, t._2.size)).toList.sortBy(_._2)
    println(finalResult3)
    val finalResult4 = lines.flatMap(_.split(" ")).map((_, 1)).groupBy(_._1).mapValues(_.size)
    val finalResult5 = lines.flatMap(_.split(" ")).map((_, 1)).groupBy(_._1).mapValues(_.foldLeft(0)(_+_._2)) //_+_._2 中第一个_代表初始值或者之前的值

    grouped.mapValues(_.size)//只对对偶中的value操作

    //
    val arr4 = Array(1,2,3,4,5)
    arr4.reduce(_+_) //相加
    arr4.reduce(_-_) //想减

    arr4.par//并发
    arr.fold(10)(_+_) // 折叠 在10基础上相加   fold传俩个参数放在俩个括号，后面的柯里化
    //聚合
    val arr7 = List(List(1,2,3), List(3,4,5), List(6,7,8))
    arr7.aggregate(0)(_+_.sum, _+_) //

    //并集 union ，交集 intersect，差集 diff

  }
}
