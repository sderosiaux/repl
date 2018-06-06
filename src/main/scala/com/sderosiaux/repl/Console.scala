package com.sderosiaux.repl

trait Console[F[_]] {
  def showPrompt(): F[Unit]
  def readCommand(): F[Command]
}
