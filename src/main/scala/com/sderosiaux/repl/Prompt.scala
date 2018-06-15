package com.sderosiaux.repl

trait Prompt[F[_], A, B] {
  def showPrompt(): F[Unit]
  def shouldExit(res: B): Boolean
  def readCommand(): F[A]
  def evalCommand(cmd: A): F[B]
}
