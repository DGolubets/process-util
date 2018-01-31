package dgolubets.system.processes

import dgolubets.system.processes.linux.LinuxProcessManager
import dgolubets.system.processes.util.{OsInfo, OsKind}
import dgolubets.system.processes.windows.WindowsProcessManager

/**
  * Abstract process interface
  */
trait Process {

  /**
    * Gets the process Id
    * @return
    */
  def id: ProcessId

  /**
    * Kills the process
    */
  def kill(): Unit

  /**
    * Kill the process group
    * or fallback to killing just the process
    */
  def killGroup(): Unit

  /**
    * Waits for the process to finish
    * @return
    */
  def waitFor(): Int

  /**
    * Gets the process status
    * @return
    */
  def isAlive: Boolean
}

object Process {

  /**
    * Get ProcessManager for current OS
    * @return
    */
  def manager: ProcessManager = {
    OsInfo.current match {
      case OsInfo(OsKind.Windows) =>
        new WindowsProcessManager()
      case OsInfo(OsKind.Linux) =>
        new LinuxProcessManager()
      case _ =>
        throw new NotImplementedError()
    }
  }
}