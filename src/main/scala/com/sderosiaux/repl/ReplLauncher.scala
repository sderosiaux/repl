package com.sderosiaux.repl

import cats.effect.{ExitCode, IO, IOApp}

object ReplLauncher extends IOApp {

  override def run(args: List[String]): IO[ExitCode] = {
    val db = new Database {
      override def tables: List[String] = List("companies", "users")
    }

    for {
      _ <- new Repl(db, new IOPrompt).loop()
    } yield ExitCode.Success
  }

}
