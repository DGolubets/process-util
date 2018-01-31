package dgolubets.system.processes.windows

import dgolubets.system.processes._

class WindowsProcessManager extends ProcessManager {

  override type P = Process

  override def start(settings: ProcessStartSettings): Process = ???
}