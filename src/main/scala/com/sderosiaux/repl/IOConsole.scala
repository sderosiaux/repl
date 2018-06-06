package com.sderosiaux.repl

import cats.effect.IO

import scala.io.StdIn

class IOConsole extends Console[IO] {
  override def showPrompt(): IO[Unit] = IO {
    print("db > ")
  }

  override def readCommand(): IO[Command] = {
    for {
      line <- IO(StdIn.readLine())
    } yield
      line match {
        case ".tables" => ShowTables
        case ".exit"   => Exit
        case s         => Query(s)
      }
  }
}
