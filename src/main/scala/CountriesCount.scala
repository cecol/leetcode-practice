
object CountriesCount {
  def main(a: Array[String]) {
    println(solution(Array(Array(5, 4, 4),
                           Array(4, 3, 4),
                           Array(3, 2, 4),
                           Array(2, 2, 2),
                           Array(3, 3, 4),
                           Array(1, 4, 4),
                           Array(4, 1, 1))))
  }

  def solution(a: Array[Array[Int]]): Int = {
    if (a == null || a.length == 0 || a(0).length == 0) return 0
    var countries = 0
    val directions = List((1, 0), (-1, 0), (0, 1), (0, -1))
    val unVisited = scala.collection.mutable.ArrayBuffer.empty[(Int, Int)]
    val visited = scala.collection.mutable.ArrayBuffer.empty[(Int, Int)]
    for (i <- a.indices) for (j <- a(i).indices) unVisited += ((i, j))
    while (unVisited.nonEmpty) {
      val (i, j) = unVisited(0)
      val unCheck = scala.collection.mutable.ArrayBuffer.empty[(Int, Int)]
      unVisited -= ((i, j))
      unCheck += ((i, j))
      while (unCheck.nonEmpty) {
        val (k, l) = unCheck(0)
        unCheck -= ((k, l))
        visited += ((k, l))
        for {
          (mx, my) <- directions
          (x, y) = (k + mx, l + my)
          if x >= 0 &&
             y >= 0 &&
             x < a.length &&
             y < a(0).length &&
             !visited.contains((x, y)) &&
             a(x)(y) == a(k)(l)
        } {
          unVisited -= ((x, y))
          unCheck += ((x, y))
        }
      }
      countries += 1
    }
    return countries
  }
}
