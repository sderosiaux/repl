package com.sderosiaux.repl.database

import cats.Show
import cats.instances.all._

sealed trait Result

object Result {
  case object Exit extends Result
  case class Tables(tables: List[String]) extends Result
  case object QueryResult extends Result
  case class Message(str: String) extends Result

  implicit val peopleShow: Show[Result] = {
    import cats.derived
    import derived.auto.show._
    derived.semi.show[Result]
  }
}


