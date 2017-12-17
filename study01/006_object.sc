/*
单例对象
Scala中没有静态方法或静态字段，可以使用object语法结构达到同样的目的
 */

object Account1 {
  private var lastNumber = 0

  def newUniqueNumber() = {
    lastNumber += 1;
    lastNumber
  }
}

//对象的构造器在该对象第一次被使用时调用
Account1.newUniqueNumber()

/*
即有实例方法，又有静态方法的类
通过类和与类同名的“伴生”对象来达到同样的目的
 */
class Account {
  //必须通过Account.来访问,不能直接调用方法newUniqueNumber()
  val id = Account.newUniqueNumber()
  private var balance = 0.0

  def deposit(amount: Double): Unit = {
    balance += amount
  }
}

//伴生对象
object Account {
  private var lastNumber = 0

  private def newUniqueNumber() = {
    lastNumber += 1;
    lastNumber
  }
}

//类和它的伴生对象可以相互访问私有特性。他们必须存在于一个源文件中


/*
一个object可以扩展类以及一个或多个特质，其结果是一个扩展了指定类以及特质的类的对象，同时拥有对象定义中给出的所有特性
 */

//默认情况，什么都不做
abstract class UndobleAction(val description: String) {
  def undo(): Unit

  def redo(): Unit
}

//DoNothingAction对象可以被所有需要这个缺省行为的地方共用
object DoNothingAction extends UndobleAction("do nothing") {
  override def undo(): Unit = {}

  override def redo(): Unit = {}
}

val map = Map("open" -> DoNothingAction, "save" -> DoNothingAction)



/*apply方法
Object(参数1,参数2,...)：使用这种表达式的时候，apply方法被调用
 */
val arr1 = Array("Mary", "had", "a", "little", "lamb") //调用的Array对象的apply方法
//不使用构造器，可以省略new关键字
val arr3 = Array(100) //调用apply方法输出一个元素（整数100）的Array[Int]
val arr2 = new Array(100) //调用构造器this(100),输出Array[Nothing],包含了100个null元素

class Account2 private(val id: Int, initialBalance: Double) {
  private var balance = initialBalance
}

object Account2 {
  def apply(initialBalance: Double
           ): Account2 =
    new Account2(newUniqueNumber(), initialBalance)


  private var lastNumber = 0

  def newUniqueNumber() = {
    lastNumber += 1;
    lastNumber
  }
}

//可以用如下代码创建实例
val acc21 = Account2(100.0)


/*
应用程序对象
每个Scala程序都必须从一个对象的main方法开始，
 */

//自己提供main方法
object Hello {
  def main(args: Array[String]): Unit = {
    println("Hello World!")
  }
}

//继承App特质
object Hello2 extends App {
  println("Hello World!")
}

//操作命令行参数
object Hello3 extends App {
  if (args.length > 0)
    println("Hello" + args(0))
  else
    println("Hello World!")
}

var f = Hello3

/*枚举
  Scala没有枚举类型，标准类库提供了一个Enumeration助手类，可以用于产出枚举
 */

object TrafficLightColor extends  Enumeration{
  val Red,Yellow,Green = Value //ID依次为0,1,2,名称依次为字段名
  val Red2 = Value(3,"stop")
  val Yellow2 = Value(10)//名称为"Yellow2"
  val Green2 = Value("Go")//ID为11
}
//如果不指定,则ID在讲前一个枚举值基础上加1，从零开始。缺省名称为字段名
TrafficLightColor.Red
TrafficLightColor.Yellow
TrafficLightColor.Green
TrafficLightColor.Red2
TrafficLightColor.Yellow2
TrafficLightColor.Green2
import TrafficLightColor._
Red2