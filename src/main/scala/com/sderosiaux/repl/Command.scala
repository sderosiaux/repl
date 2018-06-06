package com.sderosiaux.repl

sealed trait Command

object Command {

  case object ShowTables extends Command
  case object Exit extends Command
  case class Unknown(cmd: String) extends Command
  case class Query(q: String) extends Command

}

