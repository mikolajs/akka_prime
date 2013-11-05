package pl.brosbit

import akka.actor.{Actor, ActorLogging, ActorSystem, Props}

case class StartCount()

object Boot extends App {
    
    val system = ActorSystem("MySystem")
    val mainActor = system.actorOf(Props[MainActor], name = "MainCounterActor")
    mainActor ! StartCount()
    
}

