object MonkeyJump {
  def main(a: Array[String]) {
  }

  def solution(a: Array[Int], d: Int): Int = {
    // write your code in Scala 2.12
    if (a == null || a.length == 0) return -1
    if (d > a.length) return 0
    val max = a.max
    var time = 0
    var pass = passable(a, d)
    while (!pass && time <= max) {
      time += 1
      pass = passable(a.map(i => if (i == -1) -1 else if (i < time) 0 else 1), d)
    }
    if (pass) return time else -1
  }

  def passable(a: Array[Int], d: Int): Boolean = {
    var position = -1
    var pass = false
    var k = d
    while (position + d <= a.length || k > 0) {
      var k = d
      while (a(position + k) != 0) {
        k -= 1
      }
    }
    return pass
  }
}
