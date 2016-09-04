package actor

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

/**
  * Created by shota.oda on 2016/07/20.
  */
object Runner extends App {
  import akka.util.Timeout
  import scala.concurrent.duration._
  import akka.pattern.ask
  import akka.dispatch.ExecutionContexts._

  implicit val ec = global

  override def main(args: Array[String]): Unit = {
    val system = ActorSystem("System")
    val actor = system.actorOf(Props(new WordCounterActor("")))
    //TODO なにしている？
    implicit val timeout = Timeout(25.seconds)
    //                ask
    val future = actor ? StartProcessFileMsg()
    future.map { r =>
      println(s"Total number of words $r")
      system.terminate
    }
  }
}


/* *************************************************************
*  Child Actor
* **************************************************************/
case class ProcessStringMsg(string: String)
case class StringProcessedMsg(words: Integer)

class StringCounterActor extends Actor {
  def receive = {
    case ProcessStringMsg(string) =>
      val wordsInLine = string.split(" ").length
      sender ! StringProcessedMsg(wordsInLine)
    case _ => println("Error: message not recognized")
  }
}

/* *************************************************************
*  Parent Actor
* **************************************************************/
case class StartProcessFileMsg()

class WordCounterActor(filename: String) extends Actor {
  private var running = false
  private var totalLines = 0
  private var linesProcessed = 0
  private var result = 0
  private var fileSender: Option[ActorRef] = None

  /* *************************************************************
  * Partial Function？
  * http://yuroyoro.hatenablog.com/entry/20100705/1278328898
  *
  * 部分関数
  * PartialFunction[A,R]
  * Aを引数とったときに、Rを返す関数。
  * 引数がAじゃないときは、何もしない
  * **************************************************************/
  // Receive -▷ PartialFunction[Any, Unit]
  override def receive: Receive = {
    case StartProcessFileMsg() =>
      if (running) println("already running")
      else {
        running = true
        fileSender = Some(sender)
        import scala.io.Source._
        fromFile(filename).getLines.foreach { line =>
          val actor = context.actorOf(Props[StringCounterActor])
          actor ! ProcessStringMsg(line)
          totalLines += 1
        }
      }
    case StringProcessedMsg(words) =>
      result += words
      linesProcessed += 1
      if (linesProcessed == totalLines) {
        fileSender.foreach(_ ! result)
      }
    case _ => println(s"message cannot recognized")
  }
}