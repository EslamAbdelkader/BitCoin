/**
 * Generated by https://github.com/jmfayard/buildSrcVersions
 *
 * Update this file with
 *   `$ ./gradlew buildSrcVersions` */
object Libs {
    /**
     * https://developer.android.com/topic/libraries/architecture/index.html */
    const val core_testing: String = "android.arch.core:core-testing:" + Versions.core_testing

    /**
     * https://developer.android.com/topic/libraries/architecture/index.html */
    const val android_arch_lifecycle_extensions: String = "android.arch.lifecycle:extensions:" +
            Versions.android_arch_lifecycle

    /**
     * https://developer.android.com/topic/libraries/architecture/index.html */
    const val viewmodel: String = "android.arch.lifecycle:viewmodel:" +
            Versions.android_arch_lifecycle

    /**
     * http://developer.android.com/tools/extras/support-library.html */
    const val appcompat: String = "androidx.appcompat:appcompat:" + Versions.appcompat

    /**
     * http://tools.android.com */
    const val constraintlayout: String = "androidx.constraintlayout:constraintlayout:" +
            Versions.constraintlayout

    /**
     * http://developer.android.com/tools/extras/support-library.html */
    const val core_ktx: String = "androidx.core:core-ktx:" + Versions.core_ktx

    /**
     * http://developer.android.com/tools/extras/support-library.html */
    const val lifecycle_viewmodel_ktx: String = "androidx.lifecycle:lifecycle-viewmodel-ktx:" +
            Versions.lifecycle_viewmodel_ktx

    /**
     * https://developer.android.com/testing */
    const val androidx_test_runner: String = "androidx.test:runner:" + Versions.androidx_test_runner

    /**
     * https://developer.android.com/studio */
    const val com_android_tools_build_gradle: String = "com.android.tools.build:gradle:" +
            Versions.com_android_tools_build_gradle

    /**
     * https://developer.android.com/studio */
    const val lint_gradle: String = "com.android.tools.lint:lint-gradle:" + Versions.lint_gradle

    const val mpandroidchart: String = "com.github.PhilJay:MPAndroidChart:" +
            Versions.mpandroidchart

    /**
     * https://github.com/Plastix/RxSchedulerRule */
    const val rx2: String = "com.github.Plastix.RxSchedulerRule:rx2:" + Versions.rx2

    /**
     * http://developer.android.com/tools/extras/support-library.html */
    const val material: String = "com.google.android.material:material:" + Versions.material

    /**
     * https://github.com/google/dagger */
    const val dagger_compiler: String = "com.google.dagger:dagger-compiler:" +
            Versions.com_google_dagger

    /**
     * https://github.com/google/dagger */
    const val dagger: String = "com.google.dagger:dagger:" + Versions.com_google_dagger

    /**
     * https://github.com/nhaarman/mockito-kotlin */
    const val mockito_kotlin: String = "com.nhaarman.mockitokotlin2:mockito-kotlin:" +
            Versions.mockito_kotlin

    /**
     * https://github.com/square/okhttp */
    const val logging_interceptor: String = "com.squareup.okhttp3:logging-interceptor:" +
            Versions.logging_interceptor

    /**
     * https://github.com/square/retrofit/ */
    const val adapter_rxjava2: String = "com.squareup.retrofit2:adapter-rxjava2:" +
            Versions.com_squareup_retrofit2

    /**
     * https://github.com/square/retrofit/ */
    const val converter_gson: String = "com.squareup.retrofit2:converter-gson:" +
            Versions.com_squareup_retrofit2

    /**
     * https://github.com/square/retrofit/ */
    const val retrofit: String = "com.squareup.retrofit2:retrofit:" +
            Versions.com_squareup_retrofit2

    const val de_fayard_buildsrcversions_gradle_plugin: String =
            "de.fayard.buildSrcVersions:de.fayard.buildSrcVersions.gradle.plugin:" +
            Versions.de_fayard_buildsrcversions_gradle_plugin

    /**
     * https://github.com/ReactiveX/RxAndroid */
    const val rxandroid: String = "io.reactivex.rxjava2:rxandroid:" + Versions.rxandroid

    /**
     * https://github.com/ReactiveX/RxKotlin */
    const val rxkotlin: String = "io.reactivex.rxjava2:rxkotlin:" + Versions.rxkotlin

    /**
     * http://junit.org */
    const val junit: String = "junit:junit:" + Versions.junit

    /**
     * https://kotlinlang.org/ */
    const val kotlin_android_extensions_runtime: String =
            "org.jetbrains.kotlin:kotlin-android-extensions-runtime:" +
            Versions.org_jetbrains_kotlin

    /**
     * https://kotlinlang.org/ */
    const val kotlin_android_extensions: String =
            "org.jetbrains.kotlin:kotlin-android-extensions:" + Versions.org_jetbrains_kotlin

    /**
     * https://kotlinlang.org/ */
    const val kotlin_annotation_processing_gradle: String =
            "org.jetbrains.kotlin:kotlin-annotation-processing-gradle:" +
            Versions.org_jetbrains_kotlin

    /**
     * https://kotlinlang.org/ */
    const val kotlin_gradle_plugin: String = "org.jetbrains.kotlin:kotlin-gradle-plugin:" +
            Versions.org_jetbrains_kotlin

    /**
     * https://kotlinlang.org/ */
    const val kotlin_stdlib_jdk7: String = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:" +
            Versions.org_jetbrains_kotlin

    /**
     * https://github.com/mockito/mockito */
    const val mockito_core: String = "org.mockito:mockito-core:" + Versions.mockito_core
}