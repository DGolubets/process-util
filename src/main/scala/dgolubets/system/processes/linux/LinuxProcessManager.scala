package dgolubets.system.processes.linux

import com.sun.jna.Native
import dgolubets.system.processes.{ProcessManager, ProcessStartSettings}
import dgolubets.system.processes.linux.native._

class LinuxProcessManager extends ProcessManager {

  override type P = LinuxProcess


  override def start(settings: ProcessStartSettings): LinuxProcess = {
    val pid = libc.fork()
    pid match {
      case -1 =>
        throw new Exception("Error forking a process")
      case 0 =>
        if(settings.startNewGroup) {
          libc.setpgid(0, 0)
        }

        settings.env.foreach {
          case (k, v) => libc.putenv(s"$k=$v")
        }

        val status = libc.execvp(settings.command, (settings.command +: settings.args).toArray)

        if(status == -1){
          val errText = libc.strerror(Native.getLastError)
          println(errText)
          sys.process.stderr.println(errText)
          throw new Exception(errText)
        }

        libc.exit(status)
      case _ =>
        val processId = LinuxProcessId(pid)
        new LinuxProcess(processId)
    }
  }
}
