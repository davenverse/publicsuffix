# publicsuffix - Public Suffix Checking [![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.chrisdavenport/publicsuffix_2.13/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.chrisdavenport/publicsuffix_2.13) ![Code of Conduct](https://img.shields.io/badge/Code%20of%20Conduct-Scala-blue.svg)

>  A "public suffix" is one under which Internet users can (or historically could) directly register names. Some examples of public suffixes are .com, .co.uk and pvt.k12.ma.us. The Public Suffix List is a list of all known public suffixes.
> The Public Suffix List is an initiative of Mozilla, but is maintained as a community resource. It is available for use in any software, but was originally created to meet the needs of browser manufacturers. It allows browsers to, for example:
> - Avoid privacy-damaging "supercookies" being set for high-level domain name suffixes
> - Highlight the most important part of a domain name in the user interface
> - Accurately sort history entries by site

More complete documentation of the larger initiative can be found at [publicsuffix.org](https://publicsuffix.org/)

## [Head on over to the microsite](https://davenverse.github.io/publicsuffix)

## Quick Start

To use publicsuffix in an existing SBT project with Scala 2.13 or a later version, add the following dependencies to your
`build.sbt` depending on your needs:

```scala
libraryDependencies ++= Seq(
  "io.chrisdavenport" %% "publicsuffix" % "<version>"
)
```
