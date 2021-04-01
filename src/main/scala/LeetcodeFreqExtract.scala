import java.io.{BufferedReader, InputStreamReader}
import java.net.{HttpURLConnection, URL}

object LeetcodeFreqExtract {
  def main(x: Array[String]): Unit = {
    println(get("https://leetcode.com/problemset/all/?search=979"));
  }
  def get(url: String): String = {
    ""
  }
}
