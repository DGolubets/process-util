package dgolubets.system.processes.linux.native

import com.sun.jna._

trait API {
  val libc: libc = Native.loadLibrary("c", classOf[libc])
}
