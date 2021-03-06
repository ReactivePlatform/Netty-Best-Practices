import com.typesafe.sbt.SbtScalariform.ScalariformKeys
import scalariform.formatter.preferences._

name := "Netty-Best-Practice"

version := "0.1"

scalaVersion := "2.12.6"

crossJavaVersions := Seq("1.8")

scalafixSemanticdbVersion := "4.0.0"

scalacOptions in Compile ++= Seq("-encoding", "UTF-8", "-target:jvm-1.8", "-feature", "-unchecked", "-Xlog-reflective-calls", "-Xlint")

javacOptions in compile ++= Seq("-encoding", "UTF-8", "-source", "1.8", "-target", "1.8", "-Xlint:unchecked", "-XDignore.symbol.file")

resolvers += "akka" at "https://dl.bintray.com/akka/maven/"

enablePlugins(ParadoxSitePlugin)

enablePlugins(GhpagesPlugin)

enablePlugins(AutomateHeaderPlugin)

(sources in Test) := Nil

scmInfo := Some(ScmInfo(url(
  "https://github.com/ReactivePlatform/Netty-Best-Practices.git"),
  "git@github.com:ReactivePlatform/Netty-Best-Practices.git"))

git.remoteRepo := scmInfo.value.get.connection

excludeFilter in ghpagesCleanSite :=
  new FileFilter{
    def accept(f: File) = (ghpagesRepository.value / "CNAME").getCanonicalPath == f.getCanonicalPath
  } || "versions.html"

enablePlugins(ParadoxMaterialThemePlugin)

ParadoxMaterialThemePlugin.paradoxMaterialThemeSettings(Paradox)


paradoxProperties in Compile ++= Map(
  "project.name" -> "Netty-Best-Practices",
  "github.base_url" -> "https://github.com/ReactivePlatform/Netty-Best-Practices"
)

paradoxMaterialTheme in Compile ~= {
  _.withColor("red", "pink")
    .withLogoIcon("cloud")
    .withCopyright("Copyleft © 2018 netty-best-practices.reactiveplatform.xyz")
    .withRepository(uri("https://github.com/ReactivePlatform/Netty-Best-Practices.git"))
    .withSearch(tokenizer = "[\\s\\-\\.]+")
    .withSocial(
      uri("https://github.com/hepin1989")
    )
}

organizationName := "netty-best-practices.reactiveplatform.xyz"
startYear := Some(2018)
licenses += ("Apache-2.0", new URL("https://www.apache.org/licenses/LICENSE-2.0.txt"))

def setPreferences(preferences: IFormattingPreferences): IFormattingPreferences = preferences
  .setPreference(RewriteArrowSymbols, true)
  .setPreference(AlignParameters, true)
  .setPreference(AlignSingleLineCaseStatements, true)
  .setPreference(DoubleIndentConstructorArguments, false)
  .setPreference(DoubleIndentMethodDeclaration, false)
  .setPreference(DanglingCloseParenthesis, Preserve)
  .setPreference(NewlineAtEndOfFile, true)

ScalariformKeys.preferences := setPreferences(ScalariformKeys.preferences.value)
ScalariformKeys.preferences in Compile := setPreferences(ScalariformKeys.preferences.value)
ScalariformKeys.preferences in Test := setPreferences(ScalariformKeys.preferences.value)

libraryDependencies += "io.netty" % "netty-all" % "4.1.29.Final"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5"
libraryDependencies += "junit" % "junit" % "4.12"
libraryDependencies += "org.mockito" % "mockito-core" % "2.22.0"

//如果想要写更好的代码：）
//wartremoverErrors ++= Warts.all

scalafixSettings

scalafixConfigure(Compile)
