package com.ryunen344.template.api.github

import com.ryunen344.template.api.github.response.RepositoryItemResponse
import com.ryunen344.template.api.github.response.RepositoryResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.accept
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.http.ContentType
import kotlinx.serialization.ImplicitReflectionSerializer
import kotlinx.serialization.builtins.list
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import kotlin.coroutines.CoroutineContext

class GitHubApi(
    private val httpClient: HttpClient,
    private val apiEndpoint: String,
    private val coroutineDispatcherForCallback: CoroutineContext?
) {
    private val json = Json(JsonConfiguration.Stable)

    @ImplicitReflectionSerializer
    suspend fun getRepositories(since: Int?): RepositoryResponse {
        val rawResponse = httpClient.get<String> {
            url(if (since != null) "$apiEndpoint/repositories?since=$since" else "$apiEndpoint/repositories")
            accept(ContentType.Application.Json)
        }

        return RepositoryResponse(
            json.parse(
                RepositoryItemResponse.serializer().list,
                rawResponse
            )
        )
    }
}
