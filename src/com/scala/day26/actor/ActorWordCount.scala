package com.scala.day26.actor

import scala.actors.{Actor, Future}
import scala.collection.mutable.{HashSet, ListBuffer}
import scala.io.Source

/**
  * Scala中的Actor能够实现并行编程的强大功能，它是基于事件模型的并发机制，Scala是运用消息（message）的发送、接收来实现多线程的。
  * Created by Administrator on 2017/8/23.
  */
class Task extends Actor{
  override def act(): Unit = {
    loop{
      react{  //react方式会复用线程，比receive更高效
        case SubmitTask(filename) => {
          val result = Source.fromFile(filename).getLines().flatMap(_.split(" ")).map((_, 1)).toList.groupBy(_._1).mapValues(_.size)
          sender ! ResultTask(result)   // ! 发送异步消息，没有返回值
        }
        case StopTask => {
          exit()
        }
      }
    }
  }
}

case class SubmitTask(filename: String)
case class ResultTask(result: Map[String, Int])
case object StopTask

object ActorWordCount {
  def main(args: Array[String]): Unit = {
    val replySet = new HashSet[Future[Any]]()
    val resultList = new ListBuffer[ResultTask]
    val files = Array[String]("D://words.txt", "D://words.log")
    for(f <- files){
      val actor = new Task
      val reply = actor.start() !! SubmitTask(f)    // !! 发送异步消息，返回值是 Future[Any]。
      replySet += reply
    }
    while(replySet.size > 0){
      val toCompute = replySet.filter(_.isSet)
      for(r <- toCompute){
        val result = r.apply().asInstanceOf[ResultTask]
        resultList += result
        replySet -= r
      }
      Thread.sleep(100)
    }

    //汇总功能 List((hello,3),(tom,2),(aa,1))
    val fr = resultList.flatMap(_.result).groupBy(_._1).mapValues(_.foldLeft(0)(_+_._2)).toList.sortBy(_._2)
    println(fr)
  }
}
