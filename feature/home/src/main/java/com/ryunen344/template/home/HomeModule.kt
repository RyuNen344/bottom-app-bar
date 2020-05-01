package com.ryunen344.template.home

import org.koin.androidx.fragment.dsl.fragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    fragment { HomeFragment() }
    viewModel { HomeViewModel(get()) }
}
