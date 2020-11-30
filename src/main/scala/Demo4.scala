
import scala.collection.mutable

object Demo4 {
  def main(args: Array[String]): Unit = {

    val names = List("Alice", "Bob", "Nick")

    def startA( s : String ): Boolean = {
      s.startsWith("A")
    }

    //放满足条件 startA 的元素返回到names2
    //def filter(p: A => Boolean): Repr = filterImpl(p, isFlipped = false)
    //startA就是过滤的规则,如果经过这个函数处理，该元素返回true,就放入到names2,如果返回False，就过滤掉了
    val names2 = names.filter(startA)
    println(names2)


  }
}
