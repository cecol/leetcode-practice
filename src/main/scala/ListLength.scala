object ListLength {
  def main(a: Array[String]) {
    println(solution(Array(1, 4, 100, 3, 2)))
  }
  def solution(a: Array[Int]): Int = {
    if(a == null || a.length == 0) return 0
    var count = 1
    var node = a(0)
    val visited = scala.collection.mutable.Set(0) // for avoid circular list
    while (node > 0 && node < a.length && !visited.contains(a(node))) {
      node = a(node)
      count += 1
      visited += node
    }
    return count
  }
}
