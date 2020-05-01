package com.ryunen344.bottomappbar.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.ryunen344.bottomappbar.repository.github.GitHubRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.ImplicitReflectionSerializer

class HomeViewModel(gitHubRepository: GitHubRepository) : ViewModel() {

    @ImplicitReflectionSerializer
    val repositories =
        gitHubRepository.getRepositoryList(null)
            .asLiveData(Dispatchers.Default + viewModelScope.coroutineContext)
}
