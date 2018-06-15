package com.sderosiaux.repl

import cats.effect.IO
import org.scalatest.{FlatSpec, Matchers}
import cats.implicits._

class ReplTest extends FlatSpec with Matchers {

  "The Repl" should "work" in {
    val repl = new Repl[IO, String, String](new Prompt[IO, String, String] {
      override def showPrompt(): IO[Unit] = IO.unit
      override def readCommand(): IO[String] = IO.pure("hello")
      override def shouldExit(res: String): Boolean = ???
      override def evalCommand(cmd: String): IO[String] = IO("world")
    })
    repl.readAndEval.unsafeRunSync() shouldBe "world"
  }
}
