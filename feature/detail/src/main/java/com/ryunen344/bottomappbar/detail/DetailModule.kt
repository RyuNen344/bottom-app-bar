package com.ryunen344.bottomappbar.detail

import org.koin.androidx.fragment.dsl.fragment
import org.koin.dsl.module

val detailModule = module {
    fragment { DetailFragment() }
}
