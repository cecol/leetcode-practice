object ElevatorStops {
  def main(a: Array[String]) {
    println(solution(Array(60, 80, 40), Array(2, 3, 5), 5, 2, 200))
    println(solution(Array(40, 40, 100, 80, 20), Array(3, 3, 2, 2, 3), 3, 5, 200))
  }

  def solution(a: Array[Int], b: Array[Int], m: Int, x: Int, y: Int): Int = {
    if (a == null
        || b == null
        || a.length != b.length
        || a.length == 0
        || m <= 0
        || x <= 0
        || y <= 0) {
      return 0
    }
    var stop = 0
    var index = 0
    var finish = false
    while (!finish) {
      var sum = 0
      var people = 0
      val floors = scala.collection.mutable.Set.empty[Int]
      while (index < a.length && sum + a(index) <= y && people < x) {
        sum += a(index)
        floors += b(index)
        people += 1
        index += 1
      }
      stop += (floors.size + 1)
      if (index == a.length) finish = true
    }
    return stop
  }
}
