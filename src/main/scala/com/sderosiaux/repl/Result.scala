package com.sderosiaux.repl

sealed trait Result
case object None extends Result
case object Tables extends Result
case object QueryResult extends Result

