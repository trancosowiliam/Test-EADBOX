package br.com.trancosowiliam.eadbox

import android.app.Application
import br.com.trancosowiliam.eadbox.module.applicationModule
import br.com.trancosowiliam.eadbox.module.retrofitClientModule
import org.koin.android.ext.android.startKoin

class App : Application() {

    private val modules = listOf(
            applicationModule,
            retrofitClientModule
    )

    override fun onCreate() {
        super.onCreate()
        startKoin(this, modules)
    }
}