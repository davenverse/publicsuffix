package io.chrisdavenport.publicsuffix


import io.chrisdavenport.publicsuffix.rules.Rule

trait PublicSuffixPlatform {

  def fromRules(rules: List[Rule]): PublicSuffix

  lazy val global = fromRules(data.PublicSuffixDat.fromResources())

  def fromResources(classLoader: ClassLoader = Thread.currentThread().getContextClassLoader()) =
    fromRules(data.PublicSuffixDat.fromResources(classLoader))

  def fromCustomResource(resource: String, classLoader: ClassLoader = Thread.currentThread().getContextClassLoader()) = 
    fromRules(data.PublicSuffixDat.fromCustomResource(resource, classLoader))

}