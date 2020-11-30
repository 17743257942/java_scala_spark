
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object WC {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setAppName("wc1")
      .setMaster("local[2]")

    val sc = new SparkContext(conf)
    val linesRDD:RDD[String] = sc.textFile("E:\\work-20191021\\test\\wc.txt")
    val result = linesRDD.flatMap(_.split(" "))
      .map((_, 1))
      .reduceByKey(_ + _)
//      .foreach(t => println(t._1 + "..." + t._2)) //map的_1等价于List的(0)
    result.foreach(println(_))

  }


}
