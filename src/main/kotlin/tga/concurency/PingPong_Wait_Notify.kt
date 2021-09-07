package tga.concurency

import java.lang.Thread.sleep


data class PingActor(
    val synch: Object,
    val name_: String,
    val repeats: Int = 3,
    val timeoutMillis: Long = 3000,
    val action: (Int) -> Unit
) : Thread(name_) {

    override fun run() {
        var counter = 0
        while(counter < repeats) {
            synchronized(synch) {
                counter++
                action(counter)
                synch.notify()
                if (counter < repeats) synch.wait(timeoutMillis)
            }
        }
    }

}

fun main () {

    log("Start")

    val synch = Object()

    val pingThread = PingActor(synch, "ping"){ log("ping($it) ->"); sleep(100) }
    val pongThread = PingActor(synch, "pong"){ log("             <- PONG($it)"); sleep(400) }


    synchronized(synch) {
        pingThread.start()
        synch.wait()
    }

    pongThread.start()

    pingThread.join()
    pongThread.join()

    log("Finish")

}

fun log(str: String) = println(Thread.currentThread().name + " :: " + str)