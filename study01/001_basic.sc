//
6+7
val name="cahnge"
//声明值和变量
val answer = 8*5+2
0.5*answer
/*val 定义的值是一个常量，无法改变它的内容
如果要声明可变的变量，使用var
 */
var counter = 0
counter = 1
/*
 注意：不需要给出值或者变量的类型，scala会从初始化它的表达式推断出来。
  （声明值或者变量不做初始化会报错），
  不过也可以指定变量类型，如下
 */
val greeting:String = null
val greeting2:Any = "helloworld"
val xmax,ymax = 100
// message,message2都是字符串，初始化为null
val message,message2:String = null

/**
常用类型
  */
//7中数值类型Byte、Char、Short、Int、Long、Float、Double
// 一个Boolean类型
1.toString()//产生字符串"1"
1.to(10)//产生Range(1,2,3,4,5,6,7,8,9,10),调用的是RichInt的方法,1隐式转换为RichInt
//java.lang.String对象"hello"被隐式的转换为StringOps对象
"hello".intersect("world")
//使用方法做类型转换
99.toChar
99.44.toInt
"99.44".toDouble

/*
算术和操作符重载
 */
val reslt1 = 8*5+2
//是如下的方法调用*和+都是方法名
val result2 = 8.*(5).+(2)

val result3:BigInt= 9999
result3/%2
result3./%(2)

result3 /% 4

1.to(10)
1 to 10
1 to(10)

/*
scala 没有++ 和--
 */
var num = 10
num += 1
println(num)
import  scala.math._  //_字符是通配符，类型java中的*
/*
使用scala.开头的包时，可以省略scala前缀，
import  math._ //等同于上边
 */
sqrt(2)
math.sqrt(2)
scala.math.sqrt(2)


/*
scala中没有静态方法，不过有个类似的特性，叫做单例对象，
通常，一个类对应有一个伴生对象，其方法和java中的静态方法一样
 */
BigInt.probablePrime(100,scala.util.Random)

/*
不带参数Scala的方法通常不使用圆括号，如下
 */
"hello".distinct //字符串去重

"hello".apply(4) //产生0
"hello"(4) //上面的语句简写，产生0
//可以把这种用法当做()操作符的重载形式，原理是一个名为apply的方法

//使用伴生对象的apply方法是Scala中构建对象的常用方法，例如Array(1,4,9,16)返回一个数组，用的就是Array伴生对象的apply方法

"harray".patch(1,"ung",2)


import math.BigInt.probablePrime
import util.Random
var random = probablePrime(123,Random)

val num3:BigInt = 2
num3.pow(1024)

"hello".drop(2)
"hello".last
"hello".take(1)
"hello".takeRight(1)
