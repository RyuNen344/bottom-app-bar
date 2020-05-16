package com.ryunen344.bottomappbar

import android.app.Application
import com.ryunen344.bottomappbar.api.apiModule
import com.ryunen344.bottomappbar.detail.detailModule
import com.ryunen344.bottomappbar.home.homeModule
import com.ryunen344.bottomappbar.repository.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.LogcatTree
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            androidFileProperties()
            fragmentFactory()
            modules(apiModule, repositoryModule, homeModule, detailModule)
        }

        Timber.plant(LogcatTree())
    }
}
