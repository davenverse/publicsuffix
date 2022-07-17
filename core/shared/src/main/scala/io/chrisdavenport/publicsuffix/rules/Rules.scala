package io.chrisdavenport.publicsuffix.rules

import scala.annotation.tailrec

private[publicsuffix] object Rules {


  def isPublicSuffix(s: String, rules: List[Rule]): Boolean = {
    publicSuffix(s, rules) == s
  }

  def publicSuffix(s: String, rules: List[Rule]): String = {
    val host = s.split('.').reverse.toList
    val matching = matchingRules(host, rules)
    val labels = suffixFromMatchingRules(host, matching)
    labels.reverse.mkString(".")
  }

  def domain(s: String, rules: List[Rule]): Option[String] = {
    val suffix = publicSuffix(s, rules)
    if (s == suffix || suffix == "") None
    else {
      val host = s.split('.').reverse.toList
      val suffixL = suffix.split('.').reverse.toList
      if (host.length >= suffixL.length + 1) Some(host.take(suffixL.length +1).reverse.mkString("."))
      else None
    }
  }

  def suffixFromMatchingRules(host: List[String], rules: List[Rule]): List[String] = {
    val maxRulesO = rules.map(_.labels.length).maxOption
    maxRulesO.fold(List.empty[String]){maxRules => 
      val rulesAtMax = rules.filter(_.labels.length == maxRules)
      val exceptions = rulesAtMax.filter(_.isException)
      val nonExceptionRules = rulesAtMax.filterNot(_.isException)
      exceptions.collectFirst{ case e if e.labels == host && e.labels.nonEmpty => e.labels.init}
        .orElse{
          nonExceptionRules.headOption.map{rule => 
            if (rule.labels.lastOption == Some("*")) host.take(rule.labels.length)
            else rule.labels
          }
        }.getOrElse(Nil)
    }
  }

  def matchingRules(host: List[String], rules: List[Rule]): List[Rule] = {
    rules.filter(matchRules(host, _)) 
    // This is innefficient.
    // If we order our rules we should be able to short-circuit after we leave the matching TLD
    // Additionally if we were to switch to array with an inital lookup pass we could be even more
    // efficient but that requires storing that state and passing it here.
  }

  def matchRules(host: List[String], rule: Rule): Boolean = 
    matchesPaths(rule.labels, host)


  @tailrec
  def matchesPaths(rulePath: List[String], host: List[String]): Boolean = (rulePath, host) match {
    case (x :: Nil, y :: _) if (x == y) => true
    case ("*" :: Nil, _ :: _) => true
    case (x :: xs, y :: ys) if (x == y) => matchesPaths(xs,ys)
    case _ => false
  }
}