package com.ryunen344.bottomappbar.repository

import com.ryunen344.bottomappbar.repository.github.GitHubRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { GitHubRepository(get()) }
}
