import sbt._
import Keys._

object InfluxDBClientBuild extends Build {
  val Organization = "com.lukecycon"
  val Name = "InfluxDB Client"
  val Version = "0.1.0-SNAPSHOT"
  val ScalaVersion = "2.11.1"

  lazy val project = Project (
    "influxdb-scala",
    file("."),
    settings = super.settings ++ Seq(
      organization := Organization,
      name := Name,
      version := Version,
      scalaVersion := ScalaVersion,
      scalacOptions := Seq("-deprecation", "-unchecked", "-Xfatal-warnings"),
      resolvers += Classpaths.typesafeReleases,
      resolvers += "Apache repo"   at "https://repository.apache.org/content/repositories/releases",
      publishTo := {
        if(version.value.trim.endsWith("SNAPSHOT")) {
          Some(Resolver.sftp("Luke Cycon's maven", "maven.lukecycon.com", "maven.lukecycon.com/snapshots"))
        } else {
          Some(Resolver.sftp("Luke Cycon's maven", "maven.lukecycon.com", "maven.lukecycon.com/releases"))
        }
      },
      libraryDependencies ++= Seq(        
        "org.json4s" %% "json4s-jackson" % "3.2.10",
        "com.ning" % "async-http-client" % "1.8.12",
        "org.scalatest" %% "scalatest" % "2.2.0" % "test"
      )
    )
  )
}

