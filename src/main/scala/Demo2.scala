

import scala.collection.mutable

object Demo2 {
  def main(args: Array[String]): Unit = {

    var q = new mutable.Queue[Int]
    // 追加元素
    q += 1
    println(q)

    q ++= List(2, 3, 4)
    println("------------")
    println(q)

    // 删除元素 : (2, 3, 4)
    q.dequeue() //删除的是队列的第一个元素
    println(q)
    q.enqueue(9, 8, 7)
    println(q)

  }
}


