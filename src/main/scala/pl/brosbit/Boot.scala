package pl.brosbit

import akka.actor.{Actor, ActorLogging, ActorSystem, Props}

object Boot extends App {
    
    val system = ActorSystem("MySystem")
    val primeCounter = system.actorOf(Props[PrimeCounterActor], name = "PrimeCounter")
    var i = 2
    while (i < Long.MaxValue) {
      primeCounter ! i
      i += 1
    }
    
}

