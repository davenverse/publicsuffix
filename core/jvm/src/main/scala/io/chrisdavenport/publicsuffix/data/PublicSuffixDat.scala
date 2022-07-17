package io.chrisdavenport.publicsuffix.data

import io.chrisdavenport.publicsuffix.rules.Rule

object PublicSuffixDat {

  def fromResources(classLoader: ClassLoader = Thread.currentThread().getContextClassLoader()) = 
    fromCustomResource("io/chrisdavenport/publicsuffix/PublicSuffix.dat", classLoader)

  def fromCustomResource(resource: String, classLoader: ClassLoader = Thread.currentThread().getContextClassLoader()) = 
    scala.io.Source.fromResource(resource, classLoader)
    .getLines()
    .toList
    .map(Rule.fromLine)
    .collect{ case Some(s) => s}

}