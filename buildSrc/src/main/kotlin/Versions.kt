/**
 * Find which updates are available by running
 *     `$ ./gradlew buildSrcVersions`
 * This will only update the comments.
 *
 * YOU are responsible for updating manually the dependency version. */
object Versions {
    const val core_testing: String = "1.1.1" 

    const val android_arch_lifecycle: String = "1.1.1" 

    const val appcompat: String = "1.1.0-rc01" 

    const val constraintlayout: String = "1.1.3" 

    const val core_ktx: String = "1.0.2" 

    const val lifecycle_viewmodel_ktx: String = "2.2.0-alpha02" 

    const val androidx_test_runner: String = "1.2.0" 

    const val com_android_tools_build_gradle: String = "3.4.2" 

    const val lint_gradle: String = "26.4.2" 

    const val mpandroidchart: String = "v3.1.0" // available: "3.1.0"

    const val rx2: String = "1.0.2" 

    const val material: String = "1.1.0-alpha07" 

    const val com_google_dagger: String = "2.21" // available: "2.23.2"

    const val mockito_kotlin: String = "2.1.0" 

    const val logging_interceptor: String = "3.12.1" // available: "4.0.1"

    const val com_squareup_retrofit2: String = "2.6.0" 

    const val de_fayard_buildsrcversions_gradle_plugin: String = "0.3.2" 

    const val rxandroid: String = "2.1.1" 

    const val rxkotlin: String = "2.3.0" 

    const val junit: String = "4.12" 

    const val org_jetbrains_kotlin: String = "1.3.41" 

    const val mockito_core: String = "2.28.2" // available: "3.0.0"

    /**
     *
     *   To update Gradle, edit the wrapper file at path:
     *      ./gradle/wrapper/gradle-wrapper.properties
     */
    object Gradle {
        const val runningVersion: String = "5.1.1"

        const val currentVersion: String = "5.5.1"

        const val nightlyVersion: String = "5.7-20190724114955+0000"

        const val releaseCandidate: String = ""
    }
}
