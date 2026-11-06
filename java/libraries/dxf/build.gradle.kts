plugins{
    id("org.processing.library")
    alias(libs.plugins.mavenPublish)
}

processing {
    library {
        version = 1
        prettyVersion = "1.0.0"

        authors = mapOf(
            "Syrinx Digital" to "https://processing.org"
        )
        url = "https://processing.org/"
        categories = listOf("file", "exporter", "dxf")

        sentence = "DXF export library for Processing"
        paragraph =
            "This library allows you to export your Processing drawings as DXF files, which can be opened in CAD applications."

    }
}

sourceSets {
    main {
        java {
            srcDirs("src")
        }
    }
}


mavenPublishing {
    publishToMavenCentral(com.vanniktech.maven.publish.SonatypeHost.CENTRAL_PORTAL, automaticRelease = true)
    signAllPublications()
    coordinates("$group.core", name, version.toString())

    pom {
        name.set("Processing DXF")
        description.set("Processing DFX")
        url.set("https://processing.org")
        licenses {
            license {
                name.set("LGPL")
                url.set("https://www.gnu.org/licenses/lgpl-2.1.html")
            }
        }
        developers {
            developer {
                id.set("steftervelde")
                name.set("Stef Tervelde")
            }
            developer {
                id.set("benfry")
                name.set("Ben Fry")
            }
        }
        scm {
            url.set("https://github.com/Sudeep-syrinx/syrinx-visual-engine")
            connection.set("scm:git:git://github.com/Sudeep-syrinx/syrinx-visual-engine.git")
            developerConnection.set("scm:git:ssh://git@github.com/Sudeep-syrinx/syrinx-visual-engine.git")
        }
    }
}


/**
 * @deprecated Legacy task, use 'bundleLibrary' task provided by 'org.processing.library' plugin
 */
tasks.register<Copy>("createLibrary") {
    dependsOn("jar")
    into(layout.buildDirectory.dir("library"))

    from(layout.projectDirectory) {
        include("library.properties")
        include("examples/**")
    }

    from(configurations.runtimeClasspath) {
        into("library")
    }

    from(tasks.jar) {
        into("library")
        rename { "dxf.jar" }
    }
}
