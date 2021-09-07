package tga.concurency

import java.lang.Thread.sleep


class PingThread(
    val name_: String,
    val synch: Object,
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

    val pingThread = PingThread("ping", synch){ log("ping($it) ->");              sleep(100) }
    val pongThread = PingThread("pong", synch){ log("             <- PONG($it)"); sleep(400) }

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