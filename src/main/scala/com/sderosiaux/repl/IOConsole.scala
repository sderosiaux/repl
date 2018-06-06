package com.sderosiaux.repl

import cats.effect.IO

import scala.io.StdIn

class IOConsole extends Console[IO] {
  override def showPrompt(): IO[Unit] = IO {
    print("repl > ")
  }

  override def readCommand(): IO[Command] = {
    IO(StdIn.readLine()).map {
      case ".tables" => Command.ShowTables
      case ".exit" => Command.Exit
      case s => Command.Unknown(s)
    }
  }
}
