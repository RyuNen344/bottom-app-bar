package com.ryunen344.template.repository

import com.ryunen344.template.repository.github.GitHubRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { GitHubRepository(get()) }
}
