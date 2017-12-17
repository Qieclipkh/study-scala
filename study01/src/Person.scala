/**
  * @author changleying
  * @date 2017/12/17
  */
class Person{
  private var privateage = 0
  //在scala中，getter和setter分别叫做 age 和age_=
  //可以通过下边方法查看
  //scalac Person.scala
  //javap -private Person
  //重新定义getter和setter
  def age():Int = privateage
  def age_=(newValue:Int): Unit ={
    if(newValue>privateage) privateage = newValue
  }

  //不会生成getter和setter
  private[this] var status = 0

  //生成一个私有的final字段和一个getter方法，但没有setter
  val timestamp = new java.util.Date

}
