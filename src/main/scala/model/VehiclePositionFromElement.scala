package model

/**
  * Created by Kasim on 2017/7/13.
  */

class VehiclePositionFromElement(vehicleno : String, platecolor : Int,
                       positiontime : Long, accesscode : Int,
                       city : Int, curaccesscode : Int,
                       trans : Int, updatetime : Long,
                       encrypt : Int, lon : Int, lat : Int,
                       vec1 : Int, vec2 : Int, vec3 : Int,
                       direction : Int, altitude : Int,
                       state : Long, alarm : Long,
                       reserved : String, errorcode : String,
                       roadcode : Int) {
  /*
  var vehicleno : String = ""
  var platecolor : Int = 0
  var positiontime : Long = 0l
  var accesscode : Int = 0
  var city : Int = 0
  var curaccesscode : Int = 0
  var trans : Int = 0
  var updatetime : Long = 0l
  var encrypt : Int = 0
  var lon : Int = 0
  var lat : Int = 0
  var vec1 : Int = 0
  var vec2 : Int = 0
  var vec3 : Int = 0
  var direction : Int = 0
  var altitude : Int = 0
  var state : Long = 0l
  var alarm : Long = 0l
  var reserved : String = ""
  var errorcode : String = ""
  var roadcode : Int = 0
  */
  var distance : Double = 0

  def vehicleKey() : String = {
    vehicleno + "_" + platecolor.toString + "_" + accesscode
  }

  def printVehiclePosition() : String = {
    vehicleno + "_" + platecolor.toString + "_" + accesscode + "_" + positiontime
  }
}
