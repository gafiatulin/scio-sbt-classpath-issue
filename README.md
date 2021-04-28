# scio-sbt-classpath-issue

1. `sbt IntegrationTest/test` – first test fails (`scala-library` jar not staged).
2. `sbt --no-share IntegrationTest/test` – second test fails (sbt jars staged).
3. `sbt --no-global IntegrationTest/test` – same as 2.
4. `sbt --sbt-boot /some/dir IntegrationTest/test` – same as 2.
5. `sbt ++2.12.12 IntegrationTest/test` – success (`scalaVersion` doesn't match sbt's).