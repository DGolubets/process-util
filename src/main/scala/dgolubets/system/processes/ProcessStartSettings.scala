package dgolubets.system.processes

case class ProcessStartSettings(
  command: String,
  args: Seq[String] = Seq.empty,
  env: Map[String, String] = Map.empty,
  startNewGroup: Boolean = false
)