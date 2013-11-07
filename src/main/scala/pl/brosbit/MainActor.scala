package pl.brosbit

import akka.actor.{Actor, ActorLogging, ActorSystem, Props}

class MainActor extends Actor with ActorLogging {
  val primeBank = new PrimeBank(100)
  val system = ActorSystem("MySystem")
  val nextPrimeCounter = system.actorOf(Props[PrimeCounterActor], 
		  name = "PrimeCounter")
  def receive= {
    case numb:Int => {
      val n = primeBank.check(numb)
      if(n != 0L) {
        sender ! n
        log.info("State " + printBank)
      }
      
    }
  }
  
  def produceNumbers(){
    var i = 2
    while (i < Long.MaxValue) {
      val n = primeBank.check(i)
      if(n != 0L) nextPrimeCounter ! n
      i += 1
    }
  }
  
  def printBank = println(primeBank.bank.mkString(", "))
}