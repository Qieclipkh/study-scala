package com {
  package horstman {
    package impatient {

      class Employee {

      }

    }

  }

}

package org {
  package bigjava {

    class Counter {

    }

  }

}

//同一个文件中可以多个包，包和源文件的目录没有强制的关联关系

//绝对包名_root_
val arr1 = new _root_.scala.collection.mutable.ArrayBuffer[String]

//串联式包语句
package com.horstman.collection {
  //com和com.horstman的成员在这里不可见
  package people {

    class People {

    }

  }

}

//将包引入放在文件的开头,作用域和上边一样
package com.horstman.collection

package people


/*包对象
  每个包都可以有一个包对象。需要在父包中定义它
 */

package com.horstman.impatient {

  //包对象
  package object people {
    val defultName = "Change"
  }
  package people {

    class people {
      var name = defultName //从包对象拿到的常量
    }

  }

}

//包可见性
package com.horstman.impatient.people {

  class Person {
    //方法在他自己的包中可见，private[impatient] 扩展到上层包
    private[people] def description = "A person"
  }

}


//引用某个包中所有的成员
import java.awt._
//引入类或对象的所有成员
import java.awt.Color._
//任何地方都可以声明引入（类，对象，方法），效果一直延伸到包含改语句的块末尾

//重命名和隐藏方法
//选择器
import java.awt.{Color, Font}
//选择器 重命名选到的成员
import java.util.{HashMap => JavaHashMap}
//{HashMap=>_}将隐藏某个成员而不是重命名它
import java.util.{HashMap => _, _}


/*隐式引入
  每个Scala程序都隐式的引入
  import java.lang._
  import scala._  //这个引入被允许可以覆盖之前的引入
  import Predef._
 */




