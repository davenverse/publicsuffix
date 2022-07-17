package io.chrisdavenport.publicsuffix.retrieval.client

import cats.syntax.all._
import cats.effect.kernel._
import org.http4s.ember.client.EmberClientBuilder
import io.chrisdavenport.publicsuffix.PublicSuffix
import io.chrisdavenport.publicsuffix.retrieval.RulesRetrieval

object PublicSuffixRetrieval {
  def getPublicSuffix[F[_]: Async]: F[PublicSuffix] = 
    EmberClientBuilder.default[F].build
      .use(RulesRetrieval.getRules(_))
      .map(PublicSuffix.fromRules)
}