# publicsuffix - Public Suffix Checking [![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.chrisdavenport/publicsuffix_2.13/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.chrisdavenport/publicsuffix_2.13)

>  A "public suffix" is one under which Internet users can (or historically could) directly register names. Some examples of public suffixes are .com, .co.uk and pvt.k12.ma.us. The Public Suffix List is a list of all known public suffixes.
> The Public Suffix List is an initiative of Mozilla, but is maintained as a community resource. It is available for use in any software, but was originally created to meet the needs of browser manufacturers. It allows browsers to, for example:
> - Avoid privacy-damaging "supercookies" being set for high-level domain name suffixes
> - Highlight the most important part of a domain name in the user interface
> - Accurately sort history entries by site

More complete documentation of the larger initiative can be found at [publicsuffix.org](https://publicsuffix.org/)

## Quick Start

To use publicsuffix in an existing SBT project with Scala 2.13 or a later version, add the following dependencies to your
`build.sbt` depending on your needs:

```scala
libraryDependencies ++= Seq(
  "io.chrisdavenport" %% "publicsuffix" % "<version>"
)
```

## How to use

```scala mdoc
import io.chrisdavenport.publicsuffix.PublicSuffix

// On the jvm you have a global
val ps = PublicSuffix.global

ps.publicSuffix("com") // tld
ps.publicSuffix("christopherdavenport.github.io") // registrableDomain from a hard rule
ps.publicSuffix("foo.ck") // Wildcard
ps.publicSuffix("www.ck") // Exception
ps.publicSuffix("myExample.இந்தியா") // utf-8
ps.publicSuffix("baz.blah.foo.r.appspot.com") // Extra domain segments
```

A note on using it from different targets. If you are using node/jvm you can get a current version
of public suffix via `io.chrisdavenport.publicsuffix.retrieval.client.PublicSuffixRetrieval.getPublicSuffix` if you
are in the browser, you will need to use [http4s-dom](https://github.com/http4s/http4s-dom) and then load the rules via `io.chrisdavenport.publicsuffix.retrieval.Rules.retrieval.getRules(yourClient).map(PublicSuffix.fromRules)`