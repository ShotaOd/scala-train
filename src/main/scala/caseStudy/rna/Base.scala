package caseStudy.rna

/**
  * Created by Shota on 2016/09/10.
  */
abstract class Base
case object A extends Base
case object T extends Base
case object G extends Base
case object U extends Base

object Base {
  // カリー化の、ふたつ目の引数に代入
  val fromInt: Int => Base = Array(A, T, G, U)
  val toInt: Base => Int = Map(A -> 0, T -> 1, G -> 2, U -> 3)
}

object Runner extends App {


}

