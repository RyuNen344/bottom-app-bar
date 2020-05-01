package com.ryunen344.bottomappbar.api.github.response

import kotlinx.serialization.Serializable

@Serializable
data class RepositoryResponse(
    val repositories: List<RepositoryItemResponse>
)
