import org.tmt.sbt.docs.Settings
import org.tmt.sbt.docs.DocKeys._

ThisBuild / scalaVersion := "2.13.6"
ThisBuild / organization := "com.github.tmtmsoftware.sample"
ThisBuild / organizationName := "TMT Org"

ThisBuild / docsRepo := "https://github.com/mertia-himanshu/sample.git"
ThisBuild / docsParentDir := "sample"
ThisBuild / gitCurrentRepo := "https://github.com/mertia-himanshu/sample"

ThisBuild / version := {
  sys.props.get("prod.publish") match {
    case Some("true") => version.value
    case _            => "0.1.0-SNAPSHOT"
  }
}

lazy val openSite =
  Def.setting {
    Command.command("openSite") { (state) =>
      val uri = s"file://${Project.extract(state).get(siteDirectory)}/${docsParentDir.value}/${version.value}/index.html"
      state.log.info(s"Opening browser at $uri ...")
      java.awt.Desktop.getDesktop.browse(new java.net.URI(uri))
      state
    }
  }

lazy val docs = project
  .in(file("."))
  .enablePlugins(GithubPublishPlugin, ParadoxMaterialSitePlugin)
  .settings(
    ghpagesBranch := "gh-pages", // DO NOT DELETE
    commands += openSite.value,
    Settings.makeSiteMappings()
  )
