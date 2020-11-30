

import scala.actors.Actor
import scala.collection.mutable
import scala.collection.immutable
import scala.collection.mutable.{ArrayBuffer, ListBuffer, Map}
import scala.util.Random

object Hello2 {

  def main(args: Array[String]): Unit = {


    f4
    f3
    f2
    f1
    /*val actor1 = new HiActor
    actor1.start()
    actor1 ! "Hi"
    actor1 ! Charge(123, "asdf", 321)
    actor1 ! "asdf"*/

  }

  private def f4 = {

    val ch = 'V'
    val result = ch match {
      case '+' => println("ok~")
      case aa1 => "ok11~" + aa1 //result有结果 其他的没有
      case aa => println("ok~" + aa)
      case _ => println("ok~~")
    }
    println(result)


    val a = 6
    val obj = if (a == 1) 1
    else if (a == 2) "2"
    else if (a == 3) BigInt(3)
    else if (a == 4) Map("aa" -> 1)
    else if (a == 5) Map(1 -> "aa")
    else if (a == 6) Array(1, 2, 3)
    else if (a == 7) Array("aa", 1)
    else if (a == 8) Array("aa")


    val result1 = obj match {
      case i: Int => i
      case _: BigInt => Int.MaxValue
      case m: Map[String, Int] => "对象是一个字符串-数字的Map集合"
      case m: Map[Int, String] => "对象是一个数字-字符串的Map集合"
      case a: Array[String] => "对象是一个字符串数组"
      case a: Array[Int] => "对象是一个数字数组"
      case _ => "啥也不是"
    }
    println(result1)

    println("======================================")
    for (list <- Array(List(0), List(1, 0), List(0, 0, 0), List(1, 0, 0))) {
      val result = list match {
        case 0 :: Nil => "0" //
        case x :: y :: Nil => x + " " + y
        case 0 :: tail => "0 ..."
        case _ => "something else"
      }
      println(result)
    }
    println("======================================")
    println("======================================")
    // 元组匹配
    for (pair <- Array((0, 1), (1, 0), (1, 1), (1, 0, 2))) {

      val result = pair match { // pair 表示匹配的是对偶元组
        case (0, _) => "0 ..." //匹配第一个元素为0的对偶元组
        case (y, 0) => y // 匹配第二个元素为0的对偶元组
        case _ => "neither is 0" //匹配任意元组,不一定是对偶元组了.
      }
      println(result)
    }
    println("======================================")
    // 对象匹配
    object Square {
      //
      def unapply(z: Double): Option[Double] = {
        print("enter unapply..")
        Some(math.sqrt(z))
      }

      //构建对象时会被调用 ，比如 val n1 = Square(5)
      //测试代码如下:
      //    val n1 = Square(5)
      //    println("n1=" + n1)
      def apply(z: Double): Double = {
        println("enter applay")
        z * z
      }
    }

    // 模式匹配使用：
    val number: Double = 64.0
    number match {
      //说明
      //1. 当将 Square(n) 写在 case 后时，会默认调用unapply 方法(对象提取器)
      //2. number 会被 传递给def unapply(z: Double) 的 z 形参
      //2. 如果返回的是Some集合，则unapply提取器返回的结果会返回给 n 这个形参
      //3. case中对象的unapply方法(提取器)返回some集合则为匹配成功
      //4. 返回none集合则为匹配失败
      case Square(n) => println(n)
      case _ => println("nothing matched")
    }
    println("======================================")


    object Names {
      def unapplySeq(str: String): Option[Seq[String]] = {
        if (str.contains(",")) Some(str.split(","))
        else None
      }
    }
    val namesString = "Alice,Bob,Thomas"
    //说明
    //1.当case 后面的对象提取器方法的参数为多个，则会默认调用def unapplySeq() 方法
    //2,如果unapplySeq返回是Some，获取其中的值,判断得到的sequence中的元素的个数是否是三个,如果是三个，则把三个元素分别取出，
    // 赋值给first，second和third
    //3.其它的规则不变.
    namesString match {
      case Names(first, second, third) => {
        println("the string contains three people's names")
        // 打印字符串
        println(s"$first $second $third")
      }
      case _ => println("nothing matched")
    }
    println("======================================")
    val arr = Array(1, 7, 2, 9)
    val Array(first, second, _*) = arr
    println(first, second)
    println("======================================")

    val map = Map("A" -> 1, "B" -> 0, "C" -> 3)
    for ((k, v) <- map) {
      println(k + " -> " + v)
    }
    for ((k, 0) <- map) {
      // for中匹配会自动忽略失败的匹配，这里结果是 "B"->0，  "A"->1 和 "C"->3 会被忽略
      println(k + " --> " + 0)
    }
    for ((k, v) <- map if v == 0) {
      println(k + " ---> " + 0)
    }
    println("======================================")
    //当我们有一个类型为Amount的对象时，可以用模式匹配来匹配他的类型，并将属性值绑定到变量(即：把样例类对象的属性值提取到某个变量)
    abstract class Amount
    case class Dollar(value: Double) extends Amount
    case class Currency(value: Double, unit: String) extends Amount
    case object Nothing extends Amount

    for (amt <- Array(Dollar(1000.0), Currency(1000.0, "EUR"), Nothing)) {
      val result = amt match {
        //这里就会调用样例类Dollar的unapply方法提取器,得到遍历出来的Dollar(1000.0)的value属性值
        case Dollar(v) => "$" + v
        //调用 Currency样例类的提取器，得到遍历出来的Currency(1000.0, "EUR")的value属性值和unit的属性值
        case Currency(v, u) => v + " " + u
        case Nothing => ""
      }
      println(amt + ": " + result)
    }
    println("======================================")
    //说明:
    // Currency 样例类会自动生成一个 copy方法 copy(value: Double, unit: String)
    val amt = Currency(29.95, "EUR")
    val amt1 = amt.copy() // 不修改amt 的属性值，直接进行copy，类似其它oop的clnoe操作
    val amt2 = amt.copy(value = 19.95) //使用带名参数 修改 value
    val amt3 = amt.copy(unit = "CHF") //使用带名参数 修改unit
    println(amt)
    println(amt1)
    println(amt2)
    println(amt3)
    println("======================================")
    List(1, 3, 5, 9) match {
      //1.两个元素间::叫中置表达式,至少first，second两个匹配才行.
      //2.first 匹配第一个 second 匹配第二个, rest 匹配剩余部分(5,9)
      case first :: second :: rest => println(first + second + rest.length) //
      case _ => {
        println("匹配不到...")
      }
    }
    println("======================================")
    println("======================================")

  }

  private def f3 = {
    val t1 = (1, "a", "b", true, 2)
    var tup4 = 6 -> "six" -> "六"
    println(tup4)
    // 顺序号从1开始
    println(t1.productElement(0) + "  " + t1._1)
    // 定义数组的方式
    val arr1 = new Array[Int](10)
    val arr2 = Array(1, 2)
    val arr3 = ArrayBuffer[Int]()
    arr3.append(22)
    arr3(0) = 7
    // 创建二维数组
    val arr = Array.ofDim[Double](3, 4)
    arr(1)(1) = 11.11
    // 列表操作
    var list1 = List(1, 2, 3, "abc")
    val list2 = list1 :+ 4
    val list3 = 4 +: list1
    val list4 = list2 ::: list3 //list2和list3每一个元素相加
    val list5 = list1 :: list2 //list1整体加入到list2
    val list6 = 7 :: list1 ::: Nil

    println(list1)
    println(list2)
    println(list3)
    println(list4)
    println(list5)
    println(list6)
    // 列表 ListBuffer
    val lst0 = ListBuffer[Int](1, 2, 3)
    val lst1 = new ListBuffer[Int]
    lst1 += 4 //加一个元素
    lst1.append(5)
    println(lst0)
    println(lst1)
    lst0 ++= lst1 //列表相加
    println(lst0)

    //fold
    val list = List(1, 2, 3, 4)

    def minus(num1: Int, num2: Int): Int = {
      num1 - num2
    }

    println("fold  /:" + list.foldLeft(5)(minus))

    println("fold  :\\" + list.foldRight(5)(minus))

    // 扫描，即对某个集合的所有元素做fold操作
    val i8 = (1 to 5).scanLeft(5)(minus)
    println("scanLeft  " + i8)


    val sentence = "AAAAAAAAAABBBBBBBBCCCCCDDDDDDD"

    def putArry(arr: ArrayBuffer[Char], c: Char): ArrayBuffer[Char] = {

      arr.append(c) //增加
      //说明,理解putArry ,putArray的参数 和foldLeft 的本质
      //1. 返回的arr 是引用方法
      //2. 返回的arr 将会作为putArry( arr : ArrayBuffer[Char], c : Char )的arr : ArrayBuffer[Char]的实参
      //3. 因为是引用，因此arr 这个ArrayBuffer变量就会不断的增加
      arr //返回，
    }

    //创建val arr = ArrayBuffer[Char]()
    val arrb = ArrayBuffer[Char]()
    sentence.foldLeft(arrb)(putArry)
    println("arrb=" + arrb)


    val sentence2 = "AAAAAAAAAABBBBBBBBCCCCCDDDDDDD"

    def charCount(map: mutable.Map[Char, Int], c: Char): mutable.Map[Char, Int] = {
      //简单好理解的方式,这个执行有问题，待确定
      //map(c) = map.getOrElse(c, 0) + 1
      //使用元组的方式,
      map + (c -> (map.getOrElse(c, 0) + 1))

    }

    println(sentence2.foldLeft(Map[Char, Int]())(charCount))


    //解决方案1
    val lines = List("atguigu han hello ", "atguigu han aaa aaa aaa ccc ddd uuu")

    val res1 = lines.flatMap(_.split(" "))
    println("res1=" + res1)
    // res1.map 说明
    //1. 使用map，返回对偶元组 形式为
    //List((hello,1), (tom,1), (hello,1), (jerry,1), (hello,1), (jerry,1), (hello,1), (kitty,1))
    val res2 = res1.map((_, 1))
    println("res2=" + res2)
    // res2.groupBy(_._1)
    //1. 分组的根据是以元素来分组
    //2. _._1 中的第一个 _ 表示取出的各个对偶元组比如 (hello,1)
    //3. _._1 中的_1, 表示对偶元组的第一个元素，比如 hello
    //4. 因此 _._1 表示我们分组的标准是按照对偶元组的第一个元素进行分组
    //5. 返回的形式为 Map(tom -> List((tom,1)), kitty -> List((kitty,1)), jerry -> List((jerry,1), (jerry,1)), hello -> List((hello,1), (hello,1), (hello,1), (hello,1)))
    val res3 = res2.groupBy(_._1)
    println("res3=" + res3)

    // x=>(x._1, x._2.size) 传入一个匿名函数，完成统计
    //1.x 表示传入的Map中的各个元素，比如 jerry -> List((jerry,1), (jerry,1))
    //2.x._1 表示 jerry
    //3.x._2.size，表示对 List((jerry,1), (jerry,1))求size,是多少就是多少
    //4.结果是 res4=Map(han -> 2, atguigu -> 2, hello -> 1)
    //5.到此结果就出来了，但是没有排序
    val res4 = res3.map(x => (x._1, x._2.size))
    println("res4=" + res4)

    // res4.toList.sortBy(_._2)
    //1. toList先将map转成 list,为了下一步排序
    //5. sortBy就是排序,以对偶元组的第二个值排序，就是大小排序
    val res5 = res4.toList.sortBy(_._2)
    println("res5=" + res5)

    //如果希望从大到小排序，执行reverse即可
    val res6 = res5.reverse

    //方案2-------------------如下----------------------

    var res7 = lines.flatMap(_.split(" ")).map((_, 1)).groupBy(_._1) //Map(han -> List((han,1), (han,1)),
      .mapValues(_.foldLeft(0)(_ + _._2)) // 通过对每个检索到的值应用函数来转换此映射，第一次 0 + 1  第二次 1 + 1
      .toList
      .sortBy(_._2)
      .reverse
    println("res7=" + res7)

    // 拉链
    val list11 = List(1, 2, 3)
    val list22 = List(4, 5, 6)

    val list33 = list11.zip(list22)
    println("list33=" + list33)

    //创建Stream
    def numsForm(n: BigInt): Stream[BigInt] = n #:: numsForm(n + 1)

    val stream1 = numsForm(1)
    println(stream1)

    //再测试一个
    val stream2 = numsForm(10)
    println(stream2)

    println("head=" + stream1.head) //1
    println(stream1.tail) //Stream(2, ?)

    println(stream1) //Stream(1, 2, ?)
    println(stream1.tail.tail) //Stream(3, ?)
    println(stream1.tail.tail.head) //3
    println(stream1) //Stream(1, 2, 3, ?)

    def multi(x: BigInt): BigInt = {
      x * x
    }

    println(numsForm(5).map(multi)) //Stream(25,?)
    //map操作默认是对stream的第一个元素，进行map然后返回一个stream
    var stream3 = numsForm(5)
    stream3.tail //Stream(5, 6, ?)
    println(stream3.map(multi)) // Stream(25, ?)
    println(stream3.tail.map(multi)) //Stream(36, ?)


    def multiple(num: Int): Int = {
      num
    }

    def eq(i: Int): Boolean = {
      //如果i这个数字倒序后，和本身相同，则返回true
      i.toString.equals(i.toString.reverse)
    }

    //说明: 没有使用view
    //1. 对 1-100 进行遍历
    //2. map(multiple) 对 1-100进行map映射操作，这里其实就是简单的复制一份
    //3. filter(eq) 使用eq方法对新的集合进行过滤，条件为i.toString.equals(i.toString.reverse)
    val viewSquares1 = (1 to 100)
      .map(multiple)
      .filter(eq)
    println(viewSquares1)
    for (x <- viewSquares1) {
      print(x + "，")
    }
    println("\n-----------------------------")
    //使用view
    //1. 使用和前面一样，只是使用了view
    //2. view方法产出一个总是被懒执行的集合
    //3. view不会缓存数据，每次都要重新计算
    val viewSquares2 = (1 to 100)
      .view
      .map(multiple)
      .filter(eq)
    println(viewSquares2)
    for (x <- viewSquares2) {
      print(x + "，")
    }

    //说明
    //1. par 表示进行并行执行遍历
    //2. 并行执行，在输出时就无序了，在某个时间点输出哪个取决于操作系统分配
    //3. println(_) 表示输出当前遍历到的值
    (1 to 5).par.foreach(println(_))

    //说明
    //1. result1 只是输出了一个主线程
    //2. result2 输出了多个线程，表示是并行执行 .
    val result1 = (0 to 100).map { case _ => Thread.currentThread.getName }.distinct
    val result2 = (0 to 1000).par.map { case _ => Thread.currentThread.getName }.distinct
    println("------------------------")
    println(result1)
    println("------------------------")
    println(result2)

    /**
      * 1如果想在变量名、类名等定义中使用语法关键字（保留字），可以配合反引号反引号(飘号)  val `val` = 42
      * 2中置操作符：A 操作符 B 等同于 A.操作符(B)
      * 3后置操作符：A操作符 等同于 A.操作符，如果操作符定义的时候不带()则调用时不能加括号
      * //定义函数/方法的时候，省略的()
      * def ++ = "123
      * 4前置操作符，+、-、！、~等操作符A等同于A.unary_操作符
      * def unary_! = println("!!!!!!!")
      * 5赋值操作符，A 操作符= B 等同于 A = A 操作符 B  ，比如 A += B 等价 A = A + B
      */


    //    1的案例
    val `val` = 42
    println(`val`)

    //    2的案例


    val n1 = 1
    val n2 = 2
    val r1 = n1 + n2
    val r2 = n1.+(n2) //看Int的源码即可说明
    println("r1=" + r1 + " r2=" + r2)
    //    3的案例

    class Operate {
      //定义函数/方法的时候，省略的()
      def ++ = "123"

      def say: Unit = {
        println("say ok")
      }

      def say2(): Unit = {
        println("say2 ok")
      }

    }
    // 操作符
    val oper = new Operate
    // 无参函数的调用方式的说明
    //  1. 如果函数定义/声明时不省略()，则调用函数时，可以省略，也可以不省略
    //  2) 如果函数定义/声明时省略()，则调用函数时，必须省略,为了访问一致性,类似访问一个对象属性
    //  3) 上面的调用规则，也适用于其他形式的函数/方法定义
    println(oper ++)
    println(oper.++)

    oper.say // ok
    //oper.say() // error
    oper.say2 // ok
    oper.say2() // ok


    // 4的案例：

    class Operate1 {
      // 声明前置运算符
      //unary ：一元运算符
      def unary_! = println("!!!!!!!")
    }
    val oper1 = new Operate1
    !oper1 //前置运算符


  }

  private def f2 = {
    val arr = generateMockData()
    println(arr.mkString(","))
  }

  def generateMockData(): Array[String] = {
    val array = ArrayBuffer[String]()
    val random = new Random()
    // 模拟实时数据：
    // timestamp province city userid adid
    for (i <- 0 to 5) {

      val timestamp = System.currentTimeMillis()
      val province = random.nextInt(10)
      val city = province
      val adid = random.nextInt(20)
      val userid = random.nextInt(100)

      // 拼接实时数据
      array += timestamp + " " + province + " " + city + " " + userid + " " + adid
    }
    array.toArray
  }

  private def f1 {
    println("hello scala")
    println("-----------------")
    /**
      * 2,3,5,7,11
      * 4,6,10,14,22
      * 2
      * 6
      * 6
      */
    val a = Array(2, 3, 5, 7, 11)
    val res = for (elem <- a) yield 2 * elem
    val res2 = for (elem <- a if elem % 2 == 0) yield elem
    val res3 = a.filter(_ % 2 == 0).map(_ * 3)
    val res4 = a.filter {
      _ % 2 == 0
    } map {
      _ * 3
    }
    println(a.mkString(","))
    println(res.mkString(","))
    println(res2.mkString(","))
    println(res3.mkString(","))
    println(res4.mkString(","))
    println("-----------------")

    /**
      * ArrayBuffer(11, 7, 5, 3, 2)
      * 28
      * 0.0  0.0  0.0  0.0
      * 0.0  0.0  0.0  0.0
      * 0.0  0.0  8.0  0.0
      */
    val b = a.toBuffer
    println(b.sorted.reverse)
    println(a.sum)
    val matrix = Array.ofDim[Double](3, 4) // 3rows 4columns
    matrix(2)(2) = 8
    //中置表示法
    for (i <- 0 to 2) {
      for (j <- 0 to 3) {
        print(matrix(i)(j) + "  ")
      }
      println()
    }

    println("-----------------")
    /**
      * 7 -> a,3 -> b,9 -> c,5 -> d
      */
    val keys = Array(7, 3, 9, 5)
    val values = Array("a", "b", "c", "d", "e")
    val map = keys.zip(values).toMap
    println(map.mkString(","))
    "Mary has a little lamb".split(" ").sortWith(_.length < _.length).foreach(println)
    println("-----------------")


  }
}


class HiActor extends Actor {

  override def act(): Unit = {
    while (true) {
      receive {
        case "Hi" => println("Hello")
        case c: Charge => println(c)
        case _ => println("other")
      }
    }
  }
}

case class Charge(creditCardNumber: Long, merchant: String, amount: Double)



