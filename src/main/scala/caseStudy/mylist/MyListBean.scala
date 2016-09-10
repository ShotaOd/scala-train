package caseStudy.mylist

/**
  * Created by Shota on 2016/09/07.
  */
// +Ｏにしないと、共変にならない。
sealed trait MyList[+A]
case class MyCons[A](head: A, tail: MyList[A]) extends MyList[A]
case object MyNil extends MyList[Nothing]

object Runner extends App {

  /* *************************************************************
  * MyNilを、引数に取れない？
  *  => +A にしないと共変にならない。
  * **************************************************************/
  val list: MyList[String] = MyCons[String]("head", MyCons[String]("middle", MyNil))
  val nilist: MyList[String] = MyNil


  list match {
    case MyCons(h, t) => ???
    case MyNil => ???
  }
}
