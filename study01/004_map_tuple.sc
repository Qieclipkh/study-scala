import java.awt.Font

/*
映射和元组
 */

//不可变的Map[String,Int]
val map1 = Map("Alice" -> 10, "Bob" -> 3, "Cindy" -> 8)
//可变的Map
val map2 = scala.collection.mutable.Map("Alice" -> 10, "Bob" -> 3, "Cindy" -> 8)
//空的Map
val map3 = new scala.collection.immutable.HashMap[String, Int]
"Alice" -> 10
val map4 = Map(("Alice", 10), ("Bob", 3), "Cindy" -> 8) //等同于map1

//获取值
val map1Val1 = map1("Alice")
val map1Val2 = if (map1.contains("ff")) map1("ff") else 0
val map1Val3 = map1.getOrElse("ff", 0) //等同于上边写法
map1.get("Alice") //返回Option对象，值为Some(键对应的值)，要么是None

//改变映射的值
map2 += ("Bob" -> 30, "Frid" -> 7)
// 不可变映射可以这样操作
val map5 = map1 + ("Bob" -> 30, "Frid" -> 5)
var map6 = Map("Alice" -> 10, "Bob" -> 3, "Cindy" -> 8)
map6 = map6 - "Alice"

//迭代映射//for((k,v) <- 映射)  处理K V

// 反转映射 for((k,v) <- 映射)  yield(v,k)


//不可变的树形映射

val map7 = scala.collection.immutable.SortedMap("Alice" -> 10, "Frid" -> 5, "Bob" -> 3, "Cindy" -> 8)

//与java 互操作

import scala.collection.JavaConversions.mapAsScalaMap

//通过指定Scala映射类型来转发
val map8: scala.collection.mutable.Map[String, Int] = new java.util.TreeMap[String, Int]

//properties转Map
import scala.collection.JavaConversions.propertiesAsScalaMap

val map9: scala.collection.mutable.Map[String, String] = System.getProperties
for ((k, v) <- map9) {
  println(k + "=" + v)
}

//Scala映射转java映射
import scala.collection.JavaConversions.mapAsJavaMap
import java.awt.font.TextAttribute._
val map10 = Map(FAMILY->"Serif",SIZE -> 12) //Scala映射
val font = new Font(map10) //该方法时一个java映射


//元组
val tuple1 = (1,3,"size",1.0)
//访问元组的组元，元组的是从1开始
tuple1._1
tuple1._3
//使用模式匹配获取组元
val (first,second,_,_) = tuple1


//拉链操作

val arr1 = Array("A","B","C")
val arr2 = Array(1,2,3)
val pairs = arr1.zip(arr2)
pairs.toMap