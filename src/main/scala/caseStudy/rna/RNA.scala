//package caseStudy.rna
//
//import scala.collection.generic.CanBuildFrom
//import scala.collection.mutable.ArrayBuffer
//import scala.collection.{IndexedSeqLike, mutable}
//
///**
//  * Created by Shota on 2016/09/10.
//  */
//final class RNA private(
//                          val groups: Array[Int]
//                          ,val length: Int
//                        ) extends IndexedSeq[Base] with IndexedSeqLike[Base, RNA]{
//
//  import RNA._
//
//  // IndexedSeqのnewBuilderに対する必須の再実装
//  override protected[this] def newBuilder: mutable.Builder[Base, IndexedSeq[Base]] = RNA.newBuilder
//
//  // InexedSeqlのapplyに対する必須の再実装
//  def apply(idx: Int): Base = {
//    if (idx < 0 || length <= idx)
//      throw new IndexOutOfBoundsException
//    Base.fromInt(groups(idx / N) >> (idx % N * S) & M)
//  }
//
//  // 効率化のためのforeachのオプションの再実装
//  override def foreach[U](f: (Base) => U): Unit = {
//    var i = 0
//    var b = 0
//    while(i < length) {
//      b = if (i % N == 0) groups(i / N) else b >>> S
//      f(Base.fromInt(b & M))
//      i += 1
//    }
//  }
//}
//
//object RNA {
//  // グループ表現に必要なビット数
//  private val S = 2
//  // Intに収まるグループの数
//  private val N = 32 / S
//  // グループを分離するビットマスク
//  private val M = (1 << S) - 1
//  def fromSeq(buf: Seq[Base]): RNA = {
//    val groups = new Array[Int]((buf.length + N - 1) / N)
//    for (i <- 0 until buf.length)
//      groups(i / N) |= Base.toInt(buf(i)) << (i % N * S)
//    new RNA(groups, buf.length)
//  }
//  def apply(bases: Base*) = fromSeq(bases)
//
//  def newBuilder: mutable.Builder[Base, RNA] =
//    new ArrayBuffer mapResult fromSeq
//
//  implicit def canBuildFrom: CanBuildFrom[RNA, Base, RNA] = {
//    new CanBuildFrom[RNA, Base, RNA] {
//      def apply(): mutable.Builder[Base, RNA] = newBuilder
//      def apply(from: RNA):mutable.Builder[Base, RNA] = newBuilder
//
//    }
//  }
//}