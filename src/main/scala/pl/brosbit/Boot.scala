package pl.brosbit

import akka.actor.{Actor, ActorLogging, ActorSystem, Props}


object Boot  extends App {
    
    val primeBank = new PrimeBank(100)
    val system = ActorSystem("MySystem")
    val nextActor = system.actorOf(Props[PrimeCounterActor], name = "primeActor")
    println("Begins Loop")
    var i = 2L
    while (i < 1000000L) {
      val n = primeBank.check(i)
      if(n != 0L) nextActor ! n
      i += 1L
    }
    println("Ends Loop")
    saveBank
    for(i <- 5 to 25 by 5){
         println("To end %s s".format(25-i))
         Thread.sleep(5000)
    }
    println("close!")
    system.shutdown
    
  def printBank = println(primeBank.bank.mkString(", "))
  
  def saveBank {
    if(primeBank.bank.size > 0) {
       val path = System.getProperty("user.dir") + "/primies_2.txt"
       val file = new java.io.File(path)
       val output = new java.io.BufferedWriter(new java.io.FileWriter(file))
       output.write(primeBank.bank.mkString(", "))
       output.close()
    }  
  }
    
}

