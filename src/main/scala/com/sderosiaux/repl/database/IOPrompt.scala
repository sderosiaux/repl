package com.sderosiaux.repl.database

import cats.effect.IO
import com.sderosiaux.repl.Prompt

import scala.io.StdIn
import cats.implicits._

class IOPrompt(db: Database) extends Prompt[IO, Command, Result] {

  override def showPrompt(): IO[Unit] = IO(print("repl > ")) *> IO(Console.flush())

  override def readCommand(): IO[Command] = {
    IO(StdIn.readLine()).map {
      case ".tables" => Command.ShowTables
      case ".exit" => Command.Exit
      case s => Command.Unknown(s)
    }
  }

  override def evalCommand(cmd: Command): IO[Result] = cmd match {
    case Command.Exit => IO.pure(Result.Exit)
    case Command.ShowTables =>
      IO.pure(Result.Tables(db.tables))
    case Command.Unknown(c) =>
      IO.pure(Result.Message(s"Unknown command: $c"))
    case Command.Query(_) => IO.pure(Result.QueryResult)
  }

  override def shouldExit(res: Result) = res == Result.Exit
}
