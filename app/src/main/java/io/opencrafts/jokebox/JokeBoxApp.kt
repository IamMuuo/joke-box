package io.opencrafts.jokebox

import android.app.Application
import io.opencrafts.jokebox.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin

class JokeBoxApp : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@JokeBoxApp)
            modules(appModule)
        }
    }
}