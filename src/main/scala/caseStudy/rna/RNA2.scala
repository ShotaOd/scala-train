//package caseStudy.rna
//
//import scala.collection.mutable.ArrayBuffer
//import scala.collection.{IndexedSeqLike, mutable}
//
///**
//  * Created by Shota on 2016/09/10.
//  */
//final class RNA2 private(
//   val groups: Array[Int]
//  ,val length: Int
//) extends IndexedSeq[Base] with IndexedSeqLike[Base, RNA2]{
//
//  import RNA2._
//
//
//  override protected[this] def newBuilder: mutable.Builder[Base, IndexedSeq[Base]] = {
//    new ArrayBuffer[Base] mapResult fromSeq
//  }
//
//  def apply(idx: Int): Base = {
//    if (idx < 0 || length <= idx)
//      throw new IndexOutOfBoundsException
//    Base.fromInt(groups(idx / N) >> (idx % N * S) & M)
//  }
//}
//
//object RNA2 {
//  // グループ表現に必要なビット数
//  private val S = 2
//  // Intに収まるグループの数
//  private val N = 32 / S
//  // グループを分離するビットマスク
//  private val M = (1 << S) - 1
//  def fromSeq(buf: Seq[Base]): RNA2 = {
//    val groups = new Array[Int]((buf.length + N - 1) / N)
//    for (i <- 0 until buf.length)
//      groups(i / N) |= Base.toInt(buf(i)) << (i % N * S)
//    new RNA2(groups, buf.length)
//  }
//  def apply(bases: Base*) = fromSeq(bases)
//}
