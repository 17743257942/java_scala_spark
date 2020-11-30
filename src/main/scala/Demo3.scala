import scala.collection.mutable
//说明，从输出的结果看到，输出顺序和声明顺序一致。

object Demo3 {
  def main(args: Array[String]): Unit = {

    // 创建不可变映射集合Map
    // Java : key-Val
    // 不可变Map是有序，构建Map集合中，集合中的元素其实是Tuple2类型
    // 默认情况下（即没有引入其它包的情况下）,Map是不可变map
    // 分析: 为什么说Map中的元素是Tuple2 类型
    /*
    def apply[A, B](elems: (A, B)*): CC[A, B] = (newBuilder[A, B] ++= elems).result()
    (A, B)* : 这个就是一个可变参数，同时(A,B) 就是一个Tuple2 类型
     */
    val map1 = Map("Alice" -> 10, "Bob" -> 20, "Kotlin" -> 30)
    println(map1)
    // 使用元组的方式构建Map集合
    var map4 = mutable.Map(("A", 1), ("B", 2), ("C", 3))
    println(map4)
    println(map4.get("A").get)
    println(map4.get("ff"))
    var map5 = mutable.Map(("A", 1), ("B", "北京"), ("C", 3))
    map5 = map5 + ("E" -> 1, "F" -> 3)
    map5 -= ("E", "F", "G")
    println(map5)
  }
}





