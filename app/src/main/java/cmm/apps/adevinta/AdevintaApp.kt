package cmm.apps.adevinta

import android.app.Application
import androidx.lifecycle.LifecycleObserver
import cmm.apps.adevinta.di.AppDIModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class AdevintaApp : Application(), LifecycleObserver {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            allowOverride(false)
            androidLogger()
            androidContext(this@AdevintaApp)
            modules(AppDIModules.modules)
        }
    }

}