package mixin.selfAnno

/**
  * Created by Shota on 2016/07/30.
  */
object Runner {

}

object Apple extends Food("Apple")

abstract class Food (val name: String)
case class Recipe(name:String, list: List[Food], instruction: String)

trait FoodCategories {
  case class FoodCategory(name: String, foods: List[Food])
}

abstract class Database extends FoodCategories {
  def allFoods: List[Food]
  def allReceipes : List[Recipe]
  def foodNamed(name: String) = allFoods.find(_.name == name)
}


trait SimpleFoods {
  object Pear extends Food("Pear")
  val pear = Pear
  def allFoods: List[Food] = List(Apple, pear)

}

trait SimpleRecipes {

  abstract val pear: Food
  object FruitSalad extends Recipe (
    "fruid salad"
    ,List(Apple, pear)
    ,"hoge hoge hoge"
  )

  def allReceipes = List(FruitSalad)
}

object SimpleDB extends Database with SimpleFoods with SimpleRecipes
