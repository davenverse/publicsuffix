
import io.chrisdavenport.publicsuffix.PublicSuffix

object JvmResourceApproach  {
  def main(args: Array[String]): Unit = {
    // val site = "telinet.com.pg"
    val site = "haha.whatisthis.global.prod.fastly.net"
    // val site = "食狮.com.cn"
    // val site = "baz.blah.foo.r.appspot.com"

    // val site = "unknown"
    // val site = "christopherdavenport.github.io"
    val ps = PublicSuffix.global
    val suffix = ps.publicSuffix(site)
    println(suffix)
  }
}