object Libs {

    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"

    const val coroutines_core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutines_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val dagger_compiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"

    const val assisted_inject = "com.squareup.inject:assisted-inject-annotations-dagger2:${Versions.assisted_inject}"
    const val assisted_inject_processor =
        "com.squareup.inject:assisted-inject-processor-dagger2:${Versions.assisted_inject}"

    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glide_compiler = "com.github.bumptech.glide:compiler:${Versions.glide}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val okhttp_url_connection = "com.squareup.okhttp3:okhttp-urlconnection:${Versions.okhttp}"

    const val flow_redux = "com.freeletics.flowredux:flowredux:${Versions.flow_redux}"
    const val flow_redux_dsl = "com.freeletics.flowredux:dsl:${Versions.flow_redux}"

    const val decoro = "ru.tinkoff.decoro:decoro:${Versions.decoro}"
    const val scrolling_pager_indicator =
        "ru.tinkoff.scrollingpagerindicator:scrollingpagerindicator:${Versions.scrolling_pager_indicator}"

    const val shimmer_layout = "com.facebook.shimmer:shimmer:${Versions.shimmer_layout}"

    const val flipper = "com.facebook.flipper:flipper:${Versions.flipper}"
    const val soloader = "com.facebook.soloader:soloader:${Versions.soloader}"
    const val flipper_noop = "com.facebook.flipper:flipper-noop:${Versions.flipper}"
    const val flipper_network_plugin = "com.facebook.flipper:flipper-network-plugin:${Versions.flipper}"

    const val leakCanary = "com.squareup.leakcanary:leakcanary-android:2.3"

    const val barcode_scanner = "com.kroegerama:barcode-kaiteki:1.1.1"

    const val firebase_analytics = "com.google.firebase:firebase-analytics:${Versions.firebase_analytics}"
    const val firebase_crashlytics = "com.google.firebase:firebase-crashlytics:${Versions.firebase_crashlytics}"

    const val desugar_jdk = "com.android.tools:desugar_jdk_libs:${Versions.desugar_jdk}"

    const val junit = "junit:junit:4.13"
    const val truth = "com.google.truth:truth:1.0.1"

    const val anko = "org.jetbrains.anko:anko-commons:${Versions.anko}"
    const val anko_coroutines = "org.jetbrains.anko:anko-coroutines:${Versions.anko}"
    const val anko_sdk27_coroutines = "org.jetbrains.anko:anko-sdk27-coroutines:${Versions.anko}"
    const val anko_sdk27 = "org.jetbrains.anko:anko-sdk27:${Versions.anko}"

    const val adapter_delegates = "com.hannesdorfmann:adapterdelegates4-kotlin-dsl:${Versions.adapterdelegates}"

    const val kotlin_serialization_core = "org.jetbrains.kotlinx:kotlinx-serialization-core:${Versions.kotlin_serialization_core}"
    const val okhttp3_logging_interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
    const val retrofit_kotlin_serialisation_converter = "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:" + Versions.retrofit2_kotlinx_serialization_converter

    const val mockitoNHaarmann = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.Test.mockitoNHaarmann}"
    const val mockitoInline = "org.mockito:mockito-inline:${Versions.Test.mockitoInline}"
    const val mockitoCore = "org.mockito:mockito-core:${Versions.Test.mockitoInline}"
    const val mockK = "io.mockk:mockk:1.10.0"
    const val kluent = "org.amshove.kluent:kluent:${Versions.Test.kluent}"

    const val kotlin_coroutines_test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.3.9"

    const val robolectric = "org.robolectric:robolectric:${Versions.Test.robolectric}"

    const val anrWatchDog = "com.github.anrwatchdog:anrwatchdog:1.4.0"

    const val moshi_kotlin = "com.squareup.moshi:moshi-kotlin:" + Versions.moshi
    const val moshi_adapters = "com.squareup.moshi:moshi-adapters:" + Versions.moshi
    const val threetenbp = "org.threeten:threetenbp:1.4.0"
    const val kotlintest = "io.kotlintest:kotlintest:2.0.7"

    const val jackson_annotations = "com.fasterxml.jackson.core:jackson-annotations:2.10.2"
    const val jackson_core = "com.fasterxml.jackson.core:jackson-core:2.10.2"
    const val jackson_databind = "com.fasterxml.jackson.core:jackson-databind:2.10.2"
    const val jackson_datatype_jdk_8 = "com.fasterxml.jackson.datatype:jackson-datatype-jdk8:2.10.2"
    const val jackson_datatype_jsr310 = "com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.10.2"
    const val jetbrains_annotations = "org.jetbrains:annotations:13.0"
    const val reactive_streams_examples = "org.reactivestreams:reactive-streams-examples:1.0.3"
    const val reactive_streams = "org.reactivestreams:reactive-streams:1.0.3"
    const val jupiter = "org.junit.jupiter:junit-jupiter:5.5.2"

    const val koin_core = "io.insert-koin:koin-core:" + Versions.koin
    const val koin_test = "io.insert-koin:koin-test:" + Versions.koin

    const val html = "org.jetbrains.kotlinx:kotlinx-html-jvm:" + Versions.kotlinx_html_version
    const val statistics = "org.nield:kotlin-statistics:1.2.1"
}
