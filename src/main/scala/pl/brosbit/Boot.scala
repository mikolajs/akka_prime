package pl.brosbit

import akka.actor.{Actor, ActorLogging, ActorSystem, Props}

case class Greeting(who: String)

class GreetingActor extends Actor with ActorLogging {
    def receive = {
        case Greeting(who) ⇒ log.info("Hello " + who)
    }
}
object Boot extends App {
    
    val system = ActorSystem("MySystem")
    val greeter = system.actorOf(Props[GreetingActor], name = "greeter")
    greeter ! Greeting("Charlie Parker")
}

