package com.scala.day26.thread

import java.util.concurrent.{Callable, Executors, Future}

/**
  * Created by Administrator on 2017/8/24.
  */
object ThreadDemo {
  def main(args: Array[String]): Unit = {
    val pool = Executors.newFixedThreadPool(5)
//    for(i <- 1 to 10){
//      pool.execute(new Runnable {
//        override def run() = {
//          println(Thread.currentThread().getName)
//          Thread.sleep(1000)
//        }
//      })
//    }

    val f: Future[Int] = pool.submit(new Callable[Int] {
      override def call(): Int = {
        Thread.sleep(1000)
        100
      }
    })
    var status = f.isDone
    println(s"task status $status" )
    Thread.sleep(15000)

    status = f.isDone
    println(s"task status $status" )

    if(status){
      println(f.get())
    }


  }
}
