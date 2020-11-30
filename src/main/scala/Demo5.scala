object Demo5 {
  def main(args: Array[String]): Unit = {
    // 化简
    val list = List(1, 2, 3, 4, 5)

    def sum(num1: Int, num2: Int): Int = {
      num1 + num2
    }
    // ((((1 + 2) + 3) + 4) + 5) = 15
    println(list.reduceLeft(sum))
    // (1 + (2 + (3 + (4 + 5)))) = 15
    println(list.reduceRight(sum))

    val list2 = List(12, 2, 13, 43, 5)

    println(list2.reduceLeft((a: Int, b: Int) => if (a > b) b else a))

    def min(a: Int, b: Int): Int = {
      if (a > b) {
        b
      } else {
        a
      }
    }


    println(list2.reduceLeft(min))

  }

}
