package org.JsonReader_Aleksey

import org.apache.spark.sql.SparkSession
import org.json4s._
import org.json4s.jackson.JsonMethods._
import org.json4s.jackson.Serialization

object JsonReader extends App {

  case class User(id:Option[Int],country:Option[String],points:Option[Int],price:Option[Double],title:Option[String],variety:Option[String],vinery:Option[String])

  val spark = SparkSession.builder().master( master = "local" ).getOrCreate()

  val sc = spark.sparkContext

  val file = args(0)

  val json = sc.textFile(file)

  implicit val formats = Serialization.formats(NoTypeHints)

  val result = json.map(x=> parse(x).extract[User]).collect().toList
  println(result)

}
