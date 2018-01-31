package dgolubets.system.processes.linux.native

trait Constants {

  /**
    * Normal termination signal
    */
  val SIGTERM = 15

  /**
    *  dont hang in wait
    */
  val WNOHANG = 1

  /**
    * tell about stopped, untraced children
    */
  val WUNTRACED = 	2
}
