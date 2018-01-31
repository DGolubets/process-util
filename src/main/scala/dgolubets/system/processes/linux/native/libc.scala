package dgolubets.system.processes.linux.native

import com.sun.jna._
import com.sun.jna.ptr.IntByReference

trait libc extends Library {

  def kill (pid: Int, signum: Int): Int

  def system(command:String): Int

  def execve(filename: String, argv: Array[String], env: Array[String]): Int

  def execvp(filename: String, argv: Array[String]): Int

  def putenv (env: String): Int

  def fork(): Int

  def getpgid(): Int

  def getpgid(pid: Int): Int

  def waitpid(pid: Int, status: IntByReference, options: Int): Int

  def setpgid (pid: Int, pgid: Int): Int

  def strerror(errno: Int): String

  def exit(status: Int): Nothing
}