name := "binancebot"

version := "0.1"

scalaVersion := "2.11.0"

libraryDependencies ++= Seq(

  "com.typesafe.akka" %% "akka-http" % "10.0.11",
  "com.typesafe.akka" %% "akka-http-testkit" % "10.0.11" % Test
)
libraryDependencies += "com.typesafe.play" %% "play-ahc-ws-standalone" % "1.1.3"
libraryDependencies ++= Seq("com.typesafe.akka" %% "akka-actor" % "2.4.0",
  "org.twitter4j" %"twitter4j-stream" %"4.0.3",
  "com.typesafe.play" %% "play-json" % "2.4.6")

libraryDependencies += "org.scala-lang" % "scala-actors" % "2.11.7"
libraryDependencies += "com.typesafe.akka" %% "akka-stream" % "2.4.2"
libraryDependencies += "com.typesafe.akka" %% "akka-stream-kafka" % "0.19"
libraryDependencies += "com.softwaremill.reactivekafka" %% "reactive-kafka-core" % "0.10.0"
