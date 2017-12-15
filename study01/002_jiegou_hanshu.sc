//控制结构和函数

/*
2.1条件表达式
 */
var x = 3
val s = if (x > 0) 1 else -1
var s1 = 0
if (x > 0) s1 = 1 else s1 = -1
s1
x = -1
val s2 = if (x > 0) 1 //等同于  if(x>0) 1 else ()
val s3 = if (x > 0) 1 else "preeting" //s3类型是2者的超类Any

/*
块表达式和赋值

在Scala中，{}包含一系列的表达式，其结果也是一个表达式。块中最后一个表达式的值就是块的值

 */





import math.sqrt

val distance = {
  val dx = 2;
  val dx0 = 2;
  sqrt(dx * dx0)
}
//以一个赋值语句结束的块，值是Unit类型,记作()
val num3 = {
  var dx = 4;
  dx += 1
}

printf("hello, %s！you are %d years old", "change", 43)


/*
循环

while
for(i <- 表达式)  让变量i遍历右边表达式的所有值
 */
for (i <- 1 to 3) {
  println(i)
}

val str = "hello"
var sum = 0
for (i <- 0 until str.length) {
  sum += str(i)
}
sum

sum = 0
for (ch <- str) {
  sum += ch
}
sum
0 to 3
0 until 3


/*
高级for循环和for推导式
 */

for (i <- 1 to 3; j <- 1 to 3)
  print(10 * i + j + " ")

//每个生成器都能带个守卫
for (i <- 1 to 3 if i != 2; j <- 1 to 3)
  print(10 * i + j + " ")

for (i <- 1 to 3; from = 4 - i; j <- from to 3)
  print(10 * i + j + " ")
//for循环的循环体以yield开始，则该循环会构造出一个集合，每次迭代生成集合中的一个值，这类循环叫做for推导式
for (i <- 1 to 10)
  yield i % 3
//for推导式生成的集合与它第一个生成的类型兼容
for (ch <- "hello"; j <- 1 to 2)
  yield (ch + j).toChar //产生字符串
for (j <- 1 to 2; ch <- "hello")
  yield (ch + j).toChar //产生集合
//可以使用{}，以换行分隔
for {j <- 1 to 2
     ch <- "hello"
}
  yield (ch + j).toChar //产生集合


/*
函数
 */
//定义函数 函数名称、参数（必须给出参数的类型）、函数体
def abs(x: Double) = if (x > 0) 1 else -1

val d = abs(1)
val d2 = abs(-10)

def fac(n: Int) = {
  var r = 1
  for (i <- 1 to n) {
    r = r * i
  }
  r
}

val d3 = fac(3)
//对于递归函数，我们必须指定返回类型
def fac2(n: Int): Int = {
  if (n <= 0) 1 else n * fac2(n - 1)
}

val d4 = fac2(3)


/*
默认参数和带名参数
 */

def decorate(str: String, left: String = "[", right: String = "]") = {
  left + str + right
}

val d5 = decorate("hello")
val d6 = decorate("hello", "{", "}")
val d7 = decorate("hello", right = "{", left = "}")

//变长参数
def sum(args: Int*) = {
  var result = 0
  for (arg <- args) {
    result += arg
  }
  result
}
//可以使用任意多的参数调用
val d8 = sum(1, 2, 3, 4, 5)
//sum(1 to 5) 错误 1 to 5 是个Range
val d9 = sum(1 to 5: _*) //将1 to 5 作为参数序列处理 _*

def recursiveSum(args: Int*): Int = {
  if (args.length == 0) 0
  else args.head + recursiveSum(args.tail: _*)
  //head 取得序列的首个元素，tail是所有其他元素的序列
}

val d10 = recursiveSum(1, 2, 3)

/*
过程：函数没有返回值
 */
//下边2者等同
def box(s: String) {
  print(s)
}
def box2(s: String): Unit = {
  print(s)
}

/*
懒值lazy,初始化被推迟，在首次使用的时候取值
val 定义时候 取值
lazy 在首次使用时 取值
def 在每一次被使用时 取值

每次访问懒值，都会有一个方法被调用，而这个方法将会以线程安全的方式检查该值是否已被初始化
 */


/*
异常

throw 表达式有特殊的类型Nothing
 */
//在if/else中一个分支类型是Nothing,那么if/else表达式的类型就是另一个分支的类型
x = 10
//d11的类型是Int
val d11 = if(x>0) 1 else throw  new IllegalArgumentException("错误")
import java.net.MalformedURLException
import java.io.IOException
try{

}catch {
  //不过不使用捕获的异常对象，可以使用_
  case _:MalformedURLException=>print("错误的")
  //更通用的异常在更后边
  case ex:IOException=>ex.printStackTrace()
}finally {

}

val d12 = {}
var d14 = 0
val d13:Any = d14 = 1
d13
d14



