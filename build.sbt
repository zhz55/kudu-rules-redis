name := "kudu-rules-redis"

version := "1.0"

scalaVersion := "2.11.11"

libraryDependencies ++= Seq(
  "org.apache.spark" % "spark-sql_2.11" % "2.1.1" % "provided",
  "org.apache.kudu" % "kudu-client"  % "1.2.0" % "provided",
  "org.apache.kudu" % "kudu-spark2_2.11" % "1.2.0"
)
