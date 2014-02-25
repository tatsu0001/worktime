package tools


case class WorkTime(
  total: Double = 0,
  current: Double = 0,
  restDays: Int = 0,
  base: Double = 8
)

object WorkTime {
  import scopt._

  private val cmdName = "worktime"

  private def createOptParser() = {
    new OptionParser[WorkTime] (cmdName) {
      head(cmdName, "0.1")
      opt[Double]('t', "total").required.action {(t, opt) => opt.copy(total = t)}
      opt[Double]('c', "current").required.action {(c, opt) => opt.copy(current = c)}
      opt[Int]('r', "restDays").required.action {(r, opt) => opt.copy(restDays = r)}
      opt[Double]('b', "base").optional.action {(b, opt) => opt.copy(base = b)}
      }
    }

  def main(args: Array[String]): Unit = {
    createOptParser()
      .parse(args, WorkTime())
      .map { opt =>
        val worktimeRest = opt.total - opt.current
        val worktimeByDay = worktimeRest / opt.restDays
        val overtimeByDay = worktimeByDay - opt.base

        println("totalTime: " + opt.total + " h")
        println("currentTime: " + opt.current + " h")
        println("restTime: " + worktimeRest + " h")
        println("restDays: " + opt.restDays + " h")
        println("worktimeByDay: " + worktimeByDay + " h")
        println("overtimeByDay: " + overtimeByDay + " h")
      }
  }
}

