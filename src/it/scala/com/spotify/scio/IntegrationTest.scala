package com.spotify.scio

import org.apache.beam.runners.dataflow.DataflowRunner
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class IntegrationTest extends AnyFlatSpec with Matchers {
  val (sc, _) = ContextAndArgs(
    Array("--runner=Dataflow", "--region=us-central1")
  )

  val files: List[String] = RunnerContext
    .filesToStage(
      sc.options,
      classOf[DataflowRunner].getClassLoader,
      List.empty,
      List.empty
    )
    .toList

  it should "stage scala-library.jar" in {

    val stagedScalaLibJars: List[String] = files.filter { s =>
      val fileName = s.substring(s.lastIndexOf("/"))
      fileName.contains("scala-library") && fileName.endsWith(".jar")
    }

    stagedScalaLibJars should not be empty
  }

  it should "not stage sbt jars" in {
    val stagedZincJars = files.filter { s =>
      val fileName = s.substring(s.lastIndexOf("/"))
      fileName.contains("zinc-") && fileName.endsWith(".jar")
    }

    stagedZincJars shouldBe empty
  }
}
