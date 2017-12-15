/**
  * 数组
  */

//定长数组
val nums1 = new Array[Int](10)
//10个整数的数组，初始化为0
val strs1 = Array("hello", "world") //说明：已提供初始值就不需要new
strs1(0) = "google"
strs1
//变长数组：数组缓冲
import collection.mutable.ArrayBuffer

val nums2 = new ArrayBuffer[Int] //一个空的数组缓冲
//或者  new ArrayBuffer[Int]()
nums2 += 1//+=在尾端添加元素
nums2 += (1,2,3,4,5)//在尾端添加多个元素
nums2 ++= Array(6,7,8) //++=操作符追加集合
nums2.trimEnd(3)//移除后边的3个元素
nums2
nums2.insert(2,33)//在下边2之前插入（第2个参数是个变长参数）,所有在位置之后的元素都必须被平移
nums2
nums2.toArray


//遍历数组和数组缓冲
for(index <- 0 until nums1.length){
  println(nums1(index))
}
//末端取数可以 (0 until nums1.length).reverse
for(num <- nums1){//num先后被设定为a(0)，a(1)...
  println(num)
}

//数组转换
val nums3 = for(num <- nums2) yield 2*num//创建了一个类型与原集合相同的新集合
val nums4 = for(num <- nums2 if num%2 ==0) yield 2*num
val nums5 = nums2.filter(_%2==0).map(2*_)//与上边等同
nums5.sum
val nums6 = ArrayBuffer(1,7,2,9)
val nums7 = nums6.sortWith(_>_)

import util.Sorting._
val nums8 = nums6.toArray
//只能对数组不能对数组缓冲
quickSort(nums8)
nums8
//显示数组内容
val str1 = nums8.mkString("=")
val str2 = nums8.mkString("<","=",">")



//多维数组
//Array[Array[Int]]
val numsnums1 = Array.ofDim[Int](3,4)// 3行4列
numsnums1(1)(1)

//与java互操作
import collection.mutable.{ArrayBuffer,Buffer}
import collection.JavaConversions.bufferAsJavaList//隐式转换
val command = ArrayBuffer("ls","-al","/home/cay")

val pb = new ProcessBuilder(command)//scala转java,ArrayBuffer隐式转换为List

import collection.JavaConversions.asScalaBuffer
val cmd:Buffer[String] = pb.command() //java到scala转换
//不能使用ArrayBuffer，包装起来的对象仅能保证是个Buffer

//如果java方法返回一个包装过的Scala缓冲，那么隐式转换将原始的对象解包出来。
//上面的例子 command = cmd



