package com.sderosiaux.repl

sealed trait Command
case object ShowTables extends Command
case object Exit extends Command
case class Query(q: String) extends Command

