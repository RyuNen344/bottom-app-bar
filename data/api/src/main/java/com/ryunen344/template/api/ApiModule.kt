package com.ryunen344.template.api

import com.ryunen344.template.api.github.GitHubApi
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module

val apiModule = module {
    single {
        HttpClient(OkHttp) {
            engine {
                if (BuildConfig.DEBUG) {
                    addInterceptor(
                        HttpLoggingInterceptor().apply {
                            level = HttpLoggingInterceptor.Level.HEADERS

                        }
                    )
                }
            }
            install(JsonFeature) {
                serializer = KotlinxSerializer(
                    Json(
                        JsonConfiguration.Stable
                    )
                )
            }
        }
    }

    single { GitHubApi(get(), apiEndpoint(), Dispatchers.IO) }
}
