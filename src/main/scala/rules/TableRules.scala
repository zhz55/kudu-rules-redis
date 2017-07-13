package rules

import java.util.{Calendar, TimeZone}

/**
  * Created by Kasim on 2017/7/12.
  */
class TableRules {

  // 2 ways to get yesterday 00:00:00 in unixtime
  def oneDayPeriod() : Long = {
    // first way
    /*
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) -1)
    calendar.set(Calendar.HOUR_OF_DAY, 0)
    calendar.set(Calendar.MINUTE, 0)
    calendar.set(Calendar.SECOND, 0)
    calendar.getTimeInMillis / 1000
    */
    // second way
    (System.currentTimeMillis()/(1000*3600*24)*(1000*3600*24) - TimeZone.getDefault.getRawOffset - 86400000)/1000
  }


}
