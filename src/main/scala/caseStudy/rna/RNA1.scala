package caseStudy.rna

/**
  * Created by Shota on 2016/09/10.
  */
final class RNA1 private(val groups: Array[Int]
                         ,val length: Int ) extends IndexedSeq[Base] {
  import RNA1._

  def apply(idx: Int): Base = {
    if (idx < 0 || length <= idx)
      throw new IndexOutOfBoundsException
    Base.fromInt(groups(idx / N) >> (idx % N * S) & M)
  }
}

object RNA1 {
  // グループ表現に必要なビット数
  private val S = 2
  // Intに収まるグループの数
  private val N = 32 / S
  // グループを分離するビットマスク
  private val M = (1 << S) - 1
  def fromSeq(buf: Seq[Base]): RNA1 = {
    val groups = new Array[Int]((buf.length + N - 1) / N)
    for (i <- buf.indices)
      groups(i / N) |= Base.toInt(buf(i)) << (i % N * S)
    new RNA1(groups, buf.length)
  }
  def apply(bases: Base*) = fromSeq(bases)
}
