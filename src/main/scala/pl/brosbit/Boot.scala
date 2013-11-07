package pl.brosbit

import akka.actor.{Actor, ActorLogging, ActorSystem, Props}

case class PrintPrimes()

object Boot  extends App {
    
    val primeBank = new PrimeBank(100)
    val system = ActorSystem("MySystem")
    val nextActor = system.actorOf(Props[PrimeCounterActor], name = "primeActor")
    println("Begins Loop")
    var i = 2L
    while (i < 25000L) {
      val n = primeBank.check(i)
      if(n != 0L) nextActor ! n
      i += 1L
      Thread.sleep(1)
    }
    println("Ends Loop")
    nextActor ! PrintPrimes()
    Thread.sleep(1000)
    println("To end 4s")
    
    Thread.sleep(1000)
    println("To end 3s")
    
    Thread.sleep(1000)
    println("To end 2s")
    
    Thread.sleep(1000)
    println("To end 1s")
    
    Thread.sleep(1000)
    println("close!")
    system.shutdown
    
  def printBank = println(primeBank.bank.mkString(", "))
    
}

