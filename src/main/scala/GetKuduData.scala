import org.apache.kudu.spark.kudu._
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
/**
  * Created by Kasim on 2017/7/12.
  */
object GetKuduData {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("GetKuduData").setMaster("yarn")
    val sparkSession = SparkSession.builder().config(conf).getOrCreate()
    val df = sparkSession.read.options(Map("kudu.master" -> "nn01",
      "kudu.table" -> "impala::position.CTTIC_VehiclePosition_201707_2_test")).kudu
    //val kuduContext = new KuduContext("nn01")
    //kuduContext.kuduRDD(sparkSession.sparkContext, "impala::position.CTTIC_VehiclePosition_201707_err_test")
    df.createOrReplaceTempView("tmp")

    df.sqlContext.sql("select * from tmp where positiontime > 1499860032").foreach(println(_))

  }
}
