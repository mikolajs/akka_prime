    name := "Akka primes"
     
    version := "0.1"
     
    scalaVersion := "2.10.2"
     
    resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
     
    libraryDependencies ++= Seq(
    "com.typesafe.akka" %% "akka-actor" % "2.1.4",
    "org.scalatest" %% "scalatest" % "2.0" % "test"
    )


