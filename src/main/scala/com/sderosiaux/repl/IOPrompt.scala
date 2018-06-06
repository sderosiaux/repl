package com.sderosiaux.repl

import cats.effect.IO

import scala.io.StdIn

class IOPrompt extends Prompt[IO] {
  override def showPrompt(): IO[Unit] = IO {
    print("repl > ")
    Console.flush()
  }

  override def readCommand(): IO[Command] = {
    IO(StdIn.readLine()).map {
      case ".tables" => Command.ShowTables
      case ".exit" => Command.Exit
      case s => Command.Unknown(s)
    }
  }
}