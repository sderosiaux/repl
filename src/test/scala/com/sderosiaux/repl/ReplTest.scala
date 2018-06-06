package com.sderosiaux.repl

import cats.effect.IO
import org.scalatest.{FlatSpec, Matchers}

class ReplTest extends FlatSpec with Matchers {
  "The Repl" should "work" in {
    val repl = new Repl[IO](new Database {
      override def tables: List[String] = List("a", "b")
    }, new Prompt[IO] {
      override def showPrompt(): IO[Unit] = IO.unit
      override def readCommand(): IO[Command] = IO.pure(Command.ShowTables)
    })

    repl.readAndEval.unsafeRunSync() shouldBe Result.Tables(List("a", "b"))
  }
}
