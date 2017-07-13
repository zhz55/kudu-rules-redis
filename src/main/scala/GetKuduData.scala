import org.apache.kudu.spark.kudu._
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import rules.TableRules
import model.{VehiclePositionFromArray, VehiclePositionFromElement}

/**
  * Created by Kasim on 2017/7/12.
  */
object GetKuduData {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("GetKuduData").setMaster("yarn")
    val sparkSession = SparkSession.builder().config(conf).getOrCreate()
    // first way to get kudu data
    val df = sparkSession.read.options(Map("kudu.master" -> "nn01",
      "kudu.table" -> "impala::position.CTTIC_VehiclePosition_201707_2_test")).kudu
    import sparkSession.implicits._
    // second way to get kudu data
    /*
    val kuduContext = new KuduContext("nn01")
    kuduContext.kuduRDD(sparkSession.sparkContext, "impala::position.CTTIC_VehiclePosition_201707_err_test", Seq("vehicleno", "positiontime")).
      foreach {
        record => println(record.mkString(","))
      }
    */
    // 86400
    val tableRules = new TableRules

    val oneDaySql = "select * from one_day_kudu_data_table where accesscode = 110000 and positiontime > " + (tableRules.oneDayPeriod() - 1).toString +
      " and positiontime < " + (tableRules.oneDayPeriod() + 86400).toString

    df.createOrReplaceTempView("one_day_kudu_data_table")
    df.sqlContext.sql(oneDaySql).map(record => {
      val array = record.mkString(",").split(",")
      val vehiclePositionFromElement = new VehiclePositionFromElement(array(0),array(1).toInt,
        array(2).toLong,
        array(3).toInt,
        array(4).toInt,
        array(5).toInt,
        array(6).toInt,
        array(7).toLong,
        array(8).toInt,
        array(9).toInt,
        array(10).toInt,
        array(11).toInt,
        array(12).toInt,
        array(13).toInt,
        array(14).toInt,
        array(15).toInt,
        array(16).toLong,
        array(17).toLong,
        array(18),
        array(19),
        array(20).toInt)
      (vehiclePositionFromElement.vehicleKey(), vehiclePositionFromElement)
    }).rdd.groupByKey().map(record => {
      record._2.map(_.printVehiclePosition())
    }).saveAsTextFile("hdfs://nameservice1:9820/test")
  }
}
