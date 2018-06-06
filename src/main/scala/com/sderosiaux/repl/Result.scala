package com.sderosiaux.repl

sealed trait Result

object Result {
  case object Exit extends Result
  case class Tables(tables: List[String]) extends Result
  case object QueryResult extends Result
  case class Message(str: String) extends Result
}


