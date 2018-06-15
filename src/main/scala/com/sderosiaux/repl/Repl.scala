package com.sderosiaux.repl

import cats.Show
import cats.effect.Sync

class Repl[F[_], A, B: Show](prompt: Prompt[F, A, B])(implicit F: Sync[F]) {
  import cats.implicits._

  def loop(): F[Unit] = F.suspend {
    for {
      res <- readAndEval
      ex <- if (prompt.shouldExit(res)) F.unit else print(res) *> loop
    } yield ex
  }

  def readAndEval: F[B] = for {
    cmd <- read
    res <- prompt.evalCommand(cmd)
  } yield res

  private def print(res: B): F[Unit] = F.delay(println(Show[B].show(res)))

  private def read: F[A] = prompt.showPrompt() *> prompt.readCommand()

}
