package dgolubets.system.processes.linux

import com.sun.jna.ptr.IntByReference
import dgolubets.system.processes._
import dgolubets.system.processes.linux.native._

class LinuxProcess(
  val id: LinuxProcessId
) extends Process {

  private def waitFor(options: Int): Option[Int] = {
    val status = new IntByReference()
    libc.waitpid(id.pid, status, options) match {
      case 0 =>
        None
      case pid if pid == id.pid =>
        Some(status.getValue)
      case _ =>
        throw new Exception("Error waiting for process")
    }
  }

  private def kill(pid: Int, signal: Int): Unit = {
    if(libc.kill(pid, signal) == 0) {
      // we have to wait for it to die to avoid zombies
      waitFor(WNOHANG)
    } else {
      throw new Exception(s"Failed to send kill signal to $pid")
    }
  }

  override def kill(): Unit = {
    kill(id.pid, SIGTERM)
  }

  /**
    * Kill the process group
    */
  override def killGroup(): Unit = {
    val pgid = libc.getpgid(id.pid)
    if(pgid != -1) {
      kill(-pgid, SIGTERM)
    } else {
      kill()
    }
  }

  override def waitFor(): Int = {
    waitFor(0).get
  }

  /**
    * Gets the process status
    *
    * @return
    */
  override def isAlive: Boolean = {
    waitFor(WNOHANG).isEmpty
  }
}


