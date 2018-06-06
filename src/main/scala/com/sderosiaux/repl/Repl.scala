package com.sderosiaux.repl

import cats.effect.Sync

class Repl[F[_]: Sync](db: Database, prompt: Prompt[F]) {
  import cats.implicits._

  def loop(): F[Unit] = Sync[F].suspend {
    for {
      res <- readAndEval
      ex <- if (res == Result.Exit) Sync[F].unit else print(res) *> loop
    } yield ex
  }

  def readAndEval: F[Result] = for {
    cmd <- read
    res <- eval(cmd)
  } yield res

  private def print(res: Result): F[Unit] = Sync[F].delay(println(res))

  private def read: F[Command] = prompt.showPrompt() *> prompt.readCommand()

  private def eval(cmd: Command): F[Result] = {
    cmd match {
      case Command.Exit => Sync[F].delay(Result.Exit)
      case Command.ShowTables =>
        Sync[F].delay(Result.Tables(db.tables))
      case Command.Unknown(c) =>
        Sync[F].delay(Result.Message(s"Unknown command: $c"))
      case Command.Query(_) => Sync[F].delay(Result.QueryResult)
    }
  }
}
