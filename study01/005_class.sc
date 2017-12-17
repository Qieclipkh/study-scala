import scala.beans.BeanProperty
import scala.collection.mutable.ArrayBuffer

class Counter {
  var value = 0

  def increment(): Unit = {
    value += 1
  }

  def current: Int = value
}

val counter = new Counter //或 new Counter()
counter.increment()
val value = counter.current

//定义类变量，getter和setter
class Person {
  //类私有字段
  private var privateage = 0

  //在scala中，getter和setter分别叫做 age 和age_=
  //可以通过下边方法查看
  //scalac Person.scala
  //javap -private Person
  //重新定义getter和setter
  def age(): Int = privateage

  def age_=(newValue: Int): Unit = {
    if (newValue > privateage) privateage = newValue
  }

  //不会生成getter和setter,对象私有字段
  private[this] var status = 0

  //生成一个私有的final字段和一个getter方法，但没有setter
  val timestamp = new java.util.Date

}

val person = new Person
person.age_=(20)

/*
如果字段是私有的，则getter和setter方法也是私有的
如果字段为val，那么只有getter方法
如果不需要getter和setter，可以将字段声明为private[this]
private[类名](定义仅有指定类的方法可以访问给定的字段)
 */

class Person2 {

  @BeanProperty var name: String = _
  /*
  将会生成4个方法
  1.name:String
  2.name_=(newValue:String):Unit
  3.getName():String
  4.setName(newValue:String):Unit
   */


}


class Person3 {
  private var name = ""
  private var age = 0


  /**
    * 1.辅助构造器名称为this.
    * 2.每一个辅助构造器必须以一个先前已定义的其他辅助构造器或主构造器
    *
    * @param name
    */
  def this(name: String) {
    this() //调用主构造器
    this.name = name
  }

  def this(name: String, age: Int) {
    this(name) //调用前一个辅助构造器
    this.age = age
  }
}

val p1 = new Person3 //调用主构造器
val p2 = new Person3("Fred") //第一个辅助构造器
val p3 = new Person3("Fred", 42)

//第二个辅助构造器

/*
 1.主构造放在类名后边
 2.主构造器执行类定义中的所有语句
 */
class Person4(val name: String, val age: Int) {
  println("Just constructed another person")

  def description = name + " is " + age + " years old"
}

val p41 = new Person4("Fred", 42)
p41.description

/*
  主构造器私有，只能使用辅助构造器来构造Person5对象
 */
class Person5 private(val name: String, val age: Int) {
  println("Just constructed another person")

  def description = name + " is " + age + " years old"
}

/*
 嵌套类(在类中定义类)
 */
class Network {

  class Member(val name: String) {
    val contacts = new ArrayBuffer[Member]()
  }

  private val members = new ArrayBuffer[Member]()

  def join(name: String): Member = {
    val m = new Member(name)
    members += m
    m
  }
}

val chatter = new Network()
val myface = new Network()
/*
在Scala中每一个实例都有它自己的Member类，就和它们有自己的members字段一样，
chatter.Member和myface.Member是不同的2个类
 */
val fred = chatter.join("Fred")
val wilma = chatter.join("Wilma")
fred.contacts += wilma
var barny = myface.join("barny")

//类型为myface.Member
//fred.contacts += barny  这样是不可行的，不能将一个myface.Member添加到chatter.Member元素缓冲中

/*解决方法
 1.伴生对象
 */
object Network1 {

  class Member(val name: String) {
    val contacts = new ArrayBuffer[Member]()
  }

}

class Network1 {
  private val member = new ArrayBuffer[Network1.Member]()
}

//2.类型投影 Network2#Member ,含义是“任何Network2的Member”
class Network2 {

  class Member(val name: String) {
    val contacts = new ArrayBuffer[Member]()
    println(Network2.this)
  }

  private val members = new ArrayBuffer[Network2#Member]()

  def join(name: String): Member = {
    val m = new Member(name)
    members += m
    m
  }
}

/**
  * 外部类.this访问外部类的this引用
  * 或者 outer => 语法
  */

class Network3 {
  //这个名字可以用任何合法的名称
  outer =>

  class Member(val name: String) {
    val contacts = new ArrayBuffer[Member]()
    //2这一直
    println(Network3.this)
    println(outer)
  }

  private val members = new ArrayBuffer[Network3#Member]()

  def join(name: String): Member = {
    val m = new Member(name)
    members += m
    m
  }
}

val n3 = new Network3
n3.join("ff")



