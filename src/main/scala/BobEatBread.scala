object BobEatBread {
  def main(a: Array[String]) {
    bread(5, 70, 3, 2, Array(4, 6, 10, 15, 3))
    //bread2(5, 70, 3, 2, Array(4, 6, 2, 5, 3))
  }

  def bread(n: Int, w: Int, a: Int, b: Int, c: Array[Int]) = {
    var press = 0
    var minWeight = w
    for ((v, i) <- c.toList.zipWithIndex) {
      val noEat = (press + 1) * b - a
      if (noEat > v) {
        minWeight = minWeight + v
        println(s"${i + 1} day eat $minWeight")
        press = 0
      } else {
        minWeight = minWeight + noEat
        press = press + 1
        println(s"${i + 1} day not eat $minWeight")
      }
    }
    println(s"total $minWeight")
  }

  //  def bread2(n: Int, w: Int, a: Int, b: Int, c: Array[Int]) = {
  //    var press = 0
  //    val record = scala.collection.mutable.
  //
  //    for ((v, i) <- c.toList.zipWithIndex) {
  //      var t = preNoEat
  //      preNoEat = Math.min(preEat,preNoEat)
  //      preEat =
  //    }
  //    println(s"total $minWeight")
  //  }
}
