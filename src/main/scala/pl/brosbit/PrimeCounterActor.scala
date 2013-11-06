package pl.brosbit

import akka.actor.{Actor, ActorRef, ActorLogging, ActorSystem, Props}

class PrimeCounterActor extends Actor with ActorLogging {
  val primeBank = new PrimeBank(100)
  import context._
  var nextCounter:ActorRef = null
  
  def receive= {
    case numb:Int => {
      val n = primeBank.check(numb)  
    		  
      if(n != 0L) {
        if(nextCounter == null) {
          nextCounter = actorOf(Props[PrimeCounterActor], name = "PrimeCounter")
          log.info("created new Prime Actor")
        }
        nextCounter ! n
        log.info("State " + printBank)
      }
      
    }
    case p:PrintPrimes => {
      printBank
      if(nextCounter != null) nextCounter ! PrintPrimes()
      stop(self)
    }
  }
  
  def printBank = log.info(primeBank.bank.mkString(", "))
}