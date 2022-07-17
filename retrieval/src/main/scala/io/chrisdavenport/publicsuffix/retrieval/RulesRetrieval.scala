package io.chrisdavenport.publicsuffix.retrieval

import cats.syntax.all._
import cats.effect._
import io.chrisdavenport.publicsuffix.rules.Rule
import org.http4s._
import org.http4s.implicits._
import org.http4s.client.Client

object RulesRetrieval {

  def getRules[F[_]: Concurrent](c: Client[F]): F[List[Rule]] = {
    c.run(Request[F](Method.GET, uri"https://publicsuffix.org/list/public_suffix_list.dat"))
      .use(resp => 
        resp.body
          .through(fs2.text.utf8.decode)
          .through(fs2.text.lines)
          .evalMapFilter(s => Rule.fromLine(s).pure[F])
          .compile
          .to(List)
      )
  }
}