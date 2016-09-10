package caseStudy.patricia

import scala.collection._
import scala.collection.generic.CanBuildFrom
import scala.collection.mutable
/**
  * Created by Shota on 2016/09/10.
  */
class PrefixMap[T]
extends mutable.Map[String, T]
with mutable.MapLike[String, T, PrefixMap[T]] {
  var suffixes: immutable.Map[Char, PrefixMap[T]] = Map.empty
  var value: Option[T] = None

  def get(s: String): Option[T] = {
    if (s.isEmpty) value
    else suffixes get s(0) flatMap (_.get(s substring 1))
  }

  def withPrefix(s: String): PrefixMap[T] =

    if (s.isEmpty) this
    else {
      val leading = s(0)
      suffixes get leading match {
        case None =>
          suffixes = suffixes + (leading -> empty)
        case _ =>
      }
      suffixes(leading) withPrefix (s substring 1)
    }

  override def update(key: String, value: T): Unit =
    withPrefix(key).value = Some(value)

  override def remove(key: String): Option[T] =
    if (key.isEmpty) {
      val prev = value;
      value = None;
      prev
    }
    else suffixes get key(0) flatMap(_.remove(key substring 1))

  def iterator: Iterator[(String, T)] =
    (for (v <- value.iterator) yield ("", v)) ++
    (for ((chr, m) <- suffixes.iterator;
          (s, v) <- m.iterator) yield (chr +: s, v))

  def += (kv: (String, T)): this.type = {
    Array(3);
    scala.collection.generic.Builder
    List(1,2,3).size
    update(kv._1, kv._2)
    this
  }

  def -= (s: String): this.type = {
    remove(s)
    this
  }

  override def empty = new PrefixMap[T]
}


object PrefixMap extends {
  def empty[T] = new PrefixMap[T]

  def apply[T](kvs: (String, T)*): PrefixMap[T] = {
    val m: PrefixMap[T] = empty
    for(kv <- kvs) m += kv
    m
  }

  def newBuilder[T]: mutable.Builder[(String, T), PrefixMap[T]] =
    new mutable.MapBuilder[String, T, PrefixMap[T]](empty)

  implicit def canBuildFrom[T]
    : CanBuildFrom[PrefixMap[_], (String, T), PrefixMap[T]] =
    new CanBuildFrom[PrefixMap[_], (String, T), PrefixMap[T]] {
      def apply(from: PrefixMap[_]) = newBuilder[T]
      def apply() = newBuilder[T]
    }
}