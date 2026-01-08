pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
        maven {
            isAllowInsecureProtocol = true
            setUrl("http://nexus.staff.xdf.cn/repository/maven-snapshots/")}
        maven {
            isAllowInsecureProtocol = true
            setUrl("http://nexus.staff.xdf.cn/repository/maven-releases/")}
        maven { setUrl("https://jitpack.io") }
        maven { setUrl("https://maven.aliyun.com/repository/public/") }
        maven { setUrl("https://maven.aliyun.com/nexus/repository/google") }
        maven { setUrl("https://maven.aliyun.com/repository/gradle-plugin") }
        // r8 maven
        maven { setUrl("https://storage.googleapis.com/r8-releases/raw")}
        maven { setUrl("https://repo1.maven.org/maven2/")}
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            isAllowInsecureProtocol = true
            setUrl("http://nexus.staff.xdf.cn/repository/maven-snapshots/")}
        maven {
            isAllowInsecureProtocol = true
            setUrl("http://nexus.staff.xdf.cn/repository/maven-releases/")}
        maven { setUrl("https://jitpack.io") }
        maven { setUrl("https://maven.aliyun.com/repository/public/") }
        maven { setUrl("https://maven.aliyun.com/nexus/repository/google") }
        maven { setUrl("https://maven.aliyun.com/repository/gradle-plugin") }
        maven { setUrl("https://repo1.maven.org/maven2/")}

    }
}


rootProject.name = "My Application"
include(":app")
