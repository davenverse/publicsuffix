package io.chrisdavenport.publicsuffix

import io.chrisdavenport.publicsuffix.rules._

trait PublicSuffix {
  def isPublicSuffix(s: String): Boolean
  def publicSuffix(s: String): String
  def domain(s: String): Option[String]
}

object PublicSuffix extends PublicSuffixPlatform  {

  def fromRules(rules: List[Rule]): PublicSuffix = new PublicSuffixFromRules(rules)

  private class PublicSuffixFromRules(rules: List[Rule]) extends PublicSuffix {
    def isPublicSuffix(s: String): Boolean = Rules.isPublicSuffix(s, rules)
    def publicSuffix(s: String): String = Rules.publicSuffix(s, rules)
    def domain(s: String): Option[String] = Rules.domain(s, rules)
  }
}