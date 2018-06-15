package com.sderosiaux.repl.database.app

import cats.effect.{ExitCode, IO, IOApp}
import com.sderosiaux.repl.database.{Database, IOPrompt}
import com.sderosiaux.repl.Repl

object ReplApp extends IOApp {

  override def run(args: List[String]): IO[ExitCode] = {
    val db = new Database {
      override def tables: List[String] = List("companies", "users")
    }

    for {
      _ <- new Repl(new IOPrompt(db)).loop()
    } yield ExitCode.Success
  }

}
