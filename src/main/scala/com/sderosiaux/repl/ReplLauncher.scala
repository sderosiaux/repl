package com.sderosiaux.repl

import cats.effect.{ExitCode, IO, IOApp}

object ReplLauncher extends IOApp {

  override def run(args: List[String]): IO[ExitCode] = {
    for {
      _ <- new REPL(new IOConsole).loop()
    } yield ExitCode.Success
  }

}
