package dgolubets.system.processes

trait ProcessManager {

  type P <: Process

  def start(settings: ProcessStartSettings): P

  def start(command: String, args: Seq[String], env: Map[String, String] = Map.empty, startNewGroup: Boolean = true): P =
    start(ProcessStartSettings(command, args, env, startNewGroup))

  def start(command: String, args: Seq[String]): P =
    start(ProcessStartSettings(command, args, env = Map.empty, startNewGroup = true))
}
