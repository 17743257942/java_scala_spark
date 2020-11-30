package spark

import org.apache.spark.{SparkConf, SparkContext}

object TopN {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setAppName("secondsort")
      .setMaster("local[2]")
    val sc = new SparkContext(conf)
    val lines = sc.textFile("E:\\work-20191021\\test\\top10_2.txt")
    val uniqueKeys = lines.map(line => (line.split(" ")(0), line.split(" ")(1).toInt))
      .reduceByKey(_ + _)

    uniqueKeys.foreach(println(_))
    println(uniqueKeys.top(2).mkString(","))
  }


}
