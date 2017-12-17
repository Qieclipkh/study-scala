/*
使用extends扩展类，
将类声明为final，这样它不能被扩展。将单个方法和字段声明为final,确保它不会被重写
重写方法必须声明override修饰符

isInstanceOf[类名]  进行类型检查
asInstanceOf[类名] 进行类型转换

将字段或方法声明为protected,这样的成员可以被任何子类访问（对于类所属包是不可见的），但不能从其他位置看到


只有主构造器可以调用超类的构造器
 */
//Scala类可以扩展java类,这种情况下主构造器必须调用java超类的某一个构造器
class Square(x: Int, y: Int, width: Int) extends java.awt.Rectangle(x, y, width, width)

val s = new Square(1, 2, 3)

//重写字段

class Person(val name: String) {
  override def toString = getClass.getName + "[name=" + name + "]"
}

class SecretAgent(codename: String) extends Person(codename) {
  override val name = "secret"
  override val toString = "secret"
}

val secretAgent = new SecretAgent("ff")
secretAgent.toString

abstract class Person2 {
  def id: Int //这是一个抽象方法
}

class Student(override val id: Int) extends Person2 {
}

class Student2 extends Person2 {
  override def id = {
    id
  }
}

//上边边2者等同


/*
 1.def只能重写另一个def
 2.vak只能重写另一个val或不带参数的def
 3.var只能重写另一个抽象的var
 */


//匿名子类,将会创建一个结构类型的对象
val alien = new Person("Fred") {
  def greeting = "Greeting"
}
//可以用这个类型作为参数
def meet(p: Person {def greeting: String}): Unit = {
  println(p.toString + p.greeting + "     123123123123")
}
meet(alien)


//抽象类
//在子类中重写超类的抽象方法时，不需要指定override关键字

//抽象字段：就是一个没有初始值的字段
abstract class Person4 {
  val id: Int //没有初始化，是一个带有抽象的getter方法的抽象字段
  var name: String //带有抽象的getter和sttter方法的
}

//在子类中  重写超类的抽象字段，不需要override关键字
class Employee(val id: Int) extends Person4 {
  //子类有具体的id属性
  var name = "" //和具体的name属性
}

val fred = new Person4 {
  override var name: String = "Fred"
  override val id: Int = 1729
}
fred.id


/*
构造顺序和提前定义
 */

class Creature {
  val range: Int = 10
  val env: Array[Int] = new Array[Int](range) //range表达式调用了getter方法返回
}

class Ant extends Creature {
  override val range: Int = 2
}

/*
  1.Ant的构造器在做他自己的构造器之前，调用Creature的构造器
  2.Creature的构造器将它的range设为10
  3.Creature的构造器为了初始化env数组，调用range()取值器 （range的getter方法）
  4.该方法被Ant重写以输出（还未初始化）range字段值
  5.Ant的range()方法返回0.（这是对象被分配空间时所有整型字段的初始值）
  6.env被设为长度为0的Array
  7.Ant构造器继续运行，将其range设为2

  结果：env被设为长度为0的数组，教训是：在构造器中不应该依赖val的值
  解决办法1：将val声明为final
  2：在超类中将val声明为lazy
  3：在子类中使用提前定义语法
 */
//提前定义语法：在超类的构造器执行之前初始化子类的val字段
class Ant2 extends {
  override val range: Int = 2
} with Creature {

}


//对象相等行
//重写AnyRef的equals方法
class Item(val description: String, val price: Double) {
  //参数类型必须为Any
  final override def equals(obj: scala.Any): Boolean = {
    val that = obj.asInstanceOf[Item]
    if (that == null) false
    else description == that.description && price == that.price
  }

  override def hashCode(): Int = {
    13 * description.hashCode + 17 * price.hashCode()
  }
}
//在应该用程序中，并不直接调用eq或equals,只需用==操作符