package com.sderosiaux.repl

import cats.effect.Sync

class REPL[F[_]: Sync](console: Console[F]) {
  import cats.implicits._

  def loop(): F[Unit] = Sync[F].suspend {
    for {
      cmd <- read
      res <- eval(cmd)
      ex <- if (res == None) Sync[F].unit else print(res) *> loop
    } yield ex
  }

  private def print(res: Result): F[Unit] = Sync[F].delay(println(res))

  private def read: F[Command] = console.showPrompt() *> console.readCommand()

  private def eval(cmd: Command): F[Result] = {
    cmd match {
      case Exit       => Sync[F].delay(None)
      case ShowTables => Sync[F].delay(Tables)
      case Query(q)   => Sync[F].delay(QueryResult)
    }
  }
}
