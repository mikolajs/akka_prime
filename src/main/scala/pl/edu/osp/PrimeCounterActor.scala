package pl.edu.osp

import akka.actor.{Actor, ActorRef, ActorLogging, Props}

class PrimeCounterActor extends Actor with ActorLogging with BaseSave {
  log.info("creted PrimeCounterActor")
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
  }

  override def postStop(): Unit = {
    saveBank
  }



}