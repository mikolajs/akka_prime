package pl.brosbit

import akka.actor.{Actor, ActorLogging}

class PrimeCounterActor extends Actor with ActorLogging {
  val primeBank = new PrimeBank(10)
  
  def receive= {
    case numb:Int => {
      val n = primeBank.check(numb)
      if(n != 0L) {
        sender ! n
        log.info("State " + printBank)
      }
      
    }
  }
  
  def printBank = log.info(primeBank.bank.mkString(", "))
}