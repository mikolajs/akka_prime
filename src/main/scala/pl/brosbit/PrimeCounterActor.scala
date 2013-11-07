package pl.brosbit

import akka.actor.{Actor, ActorRef, ActorLogging, ActorSystem, Props}

class PrimeCounterActor extends Actor with ActorLogging {
  log.info("creted PrimeCounterActor")
  val primeBank = new PrimeBank(1000)
  import context._
  var nextCounter:ActorRef = null
  
  def receive= {
    case numb:Long => {
      val n = primeBank.check(numb)  
    		  
      if(n != 0L) {
        if(nextCounter == null) {
          nextCounter = actorOf(Props[PrimeCounterActor], name = "PrimeCounter")
          log.info("created new Prime Actor")
        }
        nextCounter ! n
      }
      
    }
    case p:PrintPrimes => {
      saveBank
      if(nextCounter != null) nextCounter ! PrintPrimes()
      stop(self)
    }
  }
  
  def printBank = log.info(primeBank.bank.mkString(", "))
  
  def saveBank {
    if(primeBank.bank.size > 0) {
       val path = "/home/ms/Programy/primies_%s.txt".format(primeBank.bank.head.toString)
       val file = new java.io.File(path)
       val output = new java.io.BufferedWriter(new java.io.FileWriter(file))
       output.write(primeBank.bank.mkString(", "))
       output.close()
    }  
  }
}