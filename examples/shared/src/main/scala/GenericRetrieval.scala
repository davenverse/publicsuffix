import cats.effect._
import io.chrisdavenport.publicsuffix.retrieval.client.PublicSuffixRetrieval

object GenericRetrieval extends IOApp {

  // Requires public-suffix-retrieval-client dependency
  def run(args: List[String]): IO[ExitCode] = 
    PublicSuffixRetrieval.getPublicSuffix[IO].flatMap{ps => 
      val s = ps.publicSuffix("christopherdavenport.github.io")
      IO.println(s)
    }.as(ExitCode.Success)

}