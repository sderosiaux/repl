package com.sderosiaux.repl

import cats.effect.Sync

class REPL[F[_]: Sync](console: Console[F]) {
  import cats.implicits._

  def loop(): F[Unit] = Sync[F].suspend {
    for {
      cmd <- read
      res <- eval(cmd)
      ex <- if (res == Result.Exit) Sync[F].unit else print(res) *> loop
    } yield ex
  }

  private def print(res: Result): F[Unit] = Sync[F].delay(println(res))

  private def read: F[Command] = console.showPrompt() *> console.readCommand()

  private def eval(cmd: Command): F[Result] = {
    cmd match {
      case Command.Exit => Sync[F].delay(Result.Exit)
      case Command.ShowTables =>
        Sync[F].delay(Result.Tables(List("toto", "titi")))
      case Command.Unknown(c) =>
        Sync[F].delay(Result.Message(s"Unknown command: $c"))
      case Command.Query(_) => Sync[F].delay(Result.QueryResult)
    }
  }
}
