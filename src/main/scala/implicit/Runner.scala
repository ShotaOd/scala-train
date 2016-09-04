package `implicit`

/**
  * Created by Shota on 2016/07/29.
  */
object Runner {
  // section03 p63
  val list = Array("a", "b", "c", "d")
  list(2)       // -> list.apply(2) -> return "c"
  list(2) = "j" // -> list.update(2, "j")
}
