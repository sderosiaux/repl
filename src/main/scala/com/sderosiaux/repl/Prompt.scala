package com.sderosiaux.repl

trait Prompt[F[_]] {
  def showPrompt(): F[Unit]
  def readCommand(): F[Command]
}
