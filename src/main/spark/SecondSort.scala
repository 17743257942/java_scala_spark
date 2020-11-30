package spark

import org.apache.spark.{SparkConf, SparkContext}
/**
  * 二次排序  自己指定排序规则
  * 自定义排序类，重写排序方法
  * 先按键分组，再处理值的集合，mapValues  tolist  sortWith(排序方法)
  */
object SecondSort {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf()
      .setAppName("secondsort")
      .setMaster("local[2]")
    val sc = new SparkContext(conf)
    val lines = sc.textFile("E:\\work-20191021\\test\\secondsort.txt")

    // key--name  value--UDFSort
    val pair = lines.map(line => (line.split(" ")(0),
      new UDFSort(line.split(" ")(1).toInt, line.split(" ")(2).toInt)))
    // 按值排序
    val result = pair.groupByKey().mapValues(
      f => f.toList.sortWith(_.compare(_) < 0)
    )

    result.foreach(println(_))
    // 验证
    result.foreach(
      x => println(x._1 + " -- " + x._2.mkString(" "))
    )


  }


}


class UDFSort(val first: Int, val second: Int) extends Ordered[UDFSort] with Serializable {

  override def compare(that: UDFSort): Int = {
    if (this.first - that.first != 0) {
      //第一个值不相等的时候，直接返回大小
      this.first - that.first //返回值
    }
    else {
      //第一个值相等的时候,比较第二个值
      this.second - that.second
    }
  }

  override def toString: String = first + ":" + second

}

