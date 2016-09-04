package conference

/**
  * section 01 ~ 05
  * Created by Shota on 2016/07/29.
  */
class Week1 {
  // TODOne メソッド呼び出しと、フィールド選択の統一アクセス原則
  // 意識しないような作りだから、意識しなくていい
  // ルールとか付けないと、カオスになるねーと。
  def hoge = "hoge"
  val fuga = "fuga"

  // TODO extractor(抽出子)

  // 式：値を返すもの
  val ifval = if (true) "true" else "false"
  val forval = for (r <- 1 to 10) r * 10               //これは値を返さない
  val foryieldval = for (r <- 1 to 10) yield r * 10    //forはyieldで返り値あり

  // TODOne List() vs Nil  どっちもよく見るけど。。。使い分け 好み

  // list 復習
  // 末尾が:(コロン)演算子 は 右結合
  val list = List("a", "b", "c", "d")
  val list2 = List("hoge", "fuga", "piyo")
  // 要素追加  :: 先頭
  val added = "e" :: list // -> list.::("e")
  // list結合 :::
  val joined = list ::: list2 // -> list2.:::(list1)

  // iterator
  import scala.io.Source
  val fileName = "/Users/Shota/Programming/Scala/train/src/main/scala/conference/Week1.scala"

  val lines = Source.fromFile(fileName).getLines.toList
  //TODO left vs right
  lines.maxBy(_.length)
  lines.max
  val max = lines.reduceLeft { (a,b) =>
    if (a.length > b.length) a else b
  }
  val widthOfLength = (num: String) => num.length.toString.length
  val widthMax = widthOfLength(max)
  for(line <- lines){
    val width = widthMax - widthOfLength(line)
    println(" " * width + line.length + "|" + line)
  }


  // 別パターン
  // val file = Source.fromFile(fileName)
  // Stream.continually(file.next()).takeWhile(c => file.hasNext) foreach println

  // TODO done self => / outer => ?
  // -> 自分型アノテーション
  /*  trait Iterator[+A] extends TraversableOnce[A] {
   *  self =>
   */

  // TODOne シンボルリテラル ?
  //'cymbal -> Symbol("cymbal")
  // 文字列をラップするようなもの
  // 実体を一つに限定する。
  // set とか、 map とかの文字列比較の時に
  // 等価比較じゃなく、等値比較するので、高速になる。
}

object Week1Runner extends App {

  override def main(args: Array[String]) = {
    val exercise = new Week1()
    val ret = exercise.forval
  }
}
