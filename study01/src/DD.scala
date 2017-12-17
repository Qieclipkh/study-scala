
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


object ff {
  def main(args: Array[String]): Unit = {
    val ant2 = new Ant2
  }
}