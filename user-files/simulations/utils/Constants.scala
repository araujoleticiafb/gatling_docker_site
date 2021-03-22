package utils

package object dem {
  val nbInicialUsers = Integer.getInteger("inicialUsers",1).toDouble
  val nbFinalUsers = Integer.getInteger("finalUsers",1).toDouble
  val myTestDuration = java.lang.Long.getLong("testDuration",10)
  val page = "2"
}