package pl.brosbit

import akka.actor.{Actor, ActorLogging, ActorSystem, Props}

class PrimeCounterActor extends Actor with ActorLogging {
  val primeBank = new PrimeBank(1000)
  val system = ActorSystem("MySystem")
  val nextCounter = system.actorOf(Props[PrimeCounterActor], name = "PrimeCounter")
  
  def receive= {
    case numb:Int => {
      val n = primeBank.check(numb)
      if(n != 0L) {
        nextCounter ! n
        log.info("State " + printBank)
      }
      
    }
  }
  
  def printBank = println(primeBank.bank.mkString(", "))
}