package collection.buffer

/**
  * Created by Shota on 2016/07/21.
  */
object Runner extends App {

  import scala.collection.mutable.ListBuffer

  /* *************************************************************
  * ListBuffer？
  * シーケンシャルなデータを扱う時のListに代わる選択肢
  * for while と使用することで、スタックオーバーフローを避けられる
  * 先頭／末尾への追加への時間が一定
  * **************************************************************/
  val bufferList = ListBuffer(2, 3, 4)
  // コロンは右結合
  1 +=: bufferList
  bufferList += 5
  println(bufferList.toList)

  /* *************************************************************
  * StringOps？
  * Predefによって、StringはStringOpsに暗黙の型変換されている
  * 何がうれしいか 一文字ずつ扱いたい時とか。
  * **************************************************************/
  val str = "hoge Piyo"
  str.exists(_.isUpper)
  // (str: String => str: StringOps) => str.exists(...)

  val fruits = "apple banana apple melon orange orange orange grape grapefruite"
  fruits.split(' ').toSet foreach println

  def wordCounts(text: String) = {
    val grouped = text.split(' ').toList.groupBy(identity)

    // map 操作
    (
        grouped.map(v => v._1 -> v._2.length) // mapping
      , grouped.mapValues(_.length)           // value => value
      , grouped.transform((k, v) => v.length) // key & value => value
      , grouped.mapValues(_.length).keys
    )
  }

  println(wordCounts(fruits)._1)
  println(wordCounts(fruits)._2)
  println(wordCounts(fruits)._3)
  println(wordCounts(fruits)._4)

  /* *************************************************************
  * 実装の最適化
  * immutable.{set, map}は、0, 1, 2, 3, 4, 5~ で実装が異なる
  * 考察：
  * Listのメソッドの実装がvarであったり、個数で実装が異なったりと
  * 処理速度に対してだいぶ気を使ってる。
  * **************************************************************/

  println("===Setの実装===============")
  println(Set().getClass)
  println(Set(1).getClass)
  println(Set(1,2).getClass)
  println(Set(1,2,3).getClass)
  println(Set(1,2,3,4).getClass)
  println(Set(1,2,3,4,5).getClass)


  println("===Mapの実装===============")
  //  println(Map().getClass)
  //  println((1 to 1).map(n => n -> n).toMap.getClass)
  //  println((1 to 2).map(n => n -> n).toMap.getClass)
  //  println((1 to 3).map(n => n -> n).toMap.getClass)
  //  println((1 to 4).map(n => n -> n).toMap.getClass)
  //  println((1 to 5).map(n => n -> n).toMap.getClass)

  // 自己満：上記の処理をちょっとイケてる感じに。。。
  // △：List.tabulate(6)(n => (1 to n).map(n => n -> n).toMap.getClass) foreach println
  (0 to 5).map(n => (1 to n).map(n => n -> n).toMap.getClass) foreach println


}
