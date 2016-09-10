package collection.api

/**
  * Created by Shota on 2016/09/09.
  */
sealed abstract class Tree
case class Branch() extends Tree
case class Node() extends Tree
