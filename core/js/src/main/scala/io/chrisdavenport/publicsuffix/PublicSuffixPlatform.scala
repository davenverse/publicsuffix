package io.chrisdavenport.publicsuffix

import io.chrisdavenport.publicsuffix.rules.Rule

trait PublicSuffixPlatform {
  def fromRules(rules: List[Rule]): PublicSuffix

}