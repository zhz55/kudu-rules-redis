import org.apache.kudu.spark.kudu.KuduContext
import org.apache.spark.SparkConf
import org.apache.spark.sql.{Row, SparkSession}
import rules.TableRules

/**
  * Created by Kasim on 2017/7/12.
  */
object GetKuduData {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("GetKuduData").setMaster("yarn")
    val sparkSession = SparkSession.builder().config(conf).getOrCreate()

    // the second way to get kudu data
    val kuduContext = new KuduContext("nn01")

    val kuduTableName = "impala::position.CTTIC_VehiclePosition_201708_4"
    val kuduTableProjColumns = Seq("vehicleno", "platecolor", "positiontime", "accesscode", "city", "curaccesscode", "trans", "lon", "lat", "state", "alarm", "errorcode")

    // 86400
    val tableRules = new TableRules

    val kuduRdd = kuduContext.kuduRDD(sparkSession.sparkContext, kuduTableName, kuduTableProjColumns)

    kuduRdd.map(record => (record(0).toString,record(1).toString.toInt,record(2).toString.toLong,
      record(3).toString.toInt,record(4).toString.toInt,record(5).toString.toInt,record(6).toString.toInt,record(7).toString.toInt,
      record(8).toString.toInt,record(9).toString.toLong,record(10).toString.toLong,record(11).toString)).
      filter(record => {
        !record._12.contains("1") && record._3 >= tableRules.oneDayPeriod() && (record._3 <= (tableRules.oneDayPeriod() + 86400))
      }).foreach(println(_))
  }
}
