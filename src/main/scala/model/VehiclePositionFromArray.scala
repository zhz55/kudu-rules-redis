package model

/**
  * Created by Kasim on 2017/7/13.
  */
class VehiclePositionFromArray(dataArray: Array[String]) {
  val vehicleno : String = dataArray(0)
  val platecolor : Int = dataArray(1).toInt
  val positiontime : Long = dataArray(2).toLong
  val accesscode : Int = dataArray(3).toInt
  val city : Int = dataArray(4).toInt
  val curaccesscode : Int = dataArray(5).toInt
  val trans : Int = dataArray(6).toInt
  val updatetime : Long = dataArray(7).toLong
  val encrypt : Int = dataArray(8).toInt
  val lon : Int = dataArray(9).toInt
  val lat : Int = dataArray(10).toInt
  val vec1 : Int = dataArray(11).toInt
  val vec2 : Int = dataArray(12).toInt
  val vec3 : Int = dataArray(13).toInt
  val direction : Int = dataArray(14).toInt
  val altitude : Int = dataArray(15).toInt
  val state : Long = dataArray(16).toLong
  val alarm : Long = dataArray(17).toLong
  val reserved : String = dataArray(18)
  val errorcode : String = dataArray(19)
  val roadcode : Int = dataArray(20).toInt

  var distance : Double = 0

  def vehicleKey() : String = {
    vehicleno + "_" + platecolor.toString + "_" + accesscode
  }

  def printVehiclePosition() : String = {
    vehicleno + "_" + platecolor.toString + "_" + accesscode + "_" + positiontime
  }
}
