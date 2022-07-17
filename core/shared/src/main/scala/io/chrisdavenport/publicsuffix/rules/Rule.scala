package io.chrisdavenport.publicsuffix.rules

case class Rule(isException: Boolean, labels: List[String])
// "foo.test.com" => com :: test :: foo :: Nil

object Rule {
  def fromLine(line: String): Option[Rule] = {
    val s = line.takeWhile(_ != ' ')
    if (s == "" || s.startsWith("//")) None
    else {
      if (s.startsWith("!")) Some(Rule(true, s.drop(1).split('.').reverse.toList))
      else Some(Rule(false, s.split('.').reverse.toList))
    }
  }
}