package iftm.filipe.com.iftransparenciav2

import android.app.Activity
import android.app.Application
import com.crashlytics.android.Crashlytics
import com.crashlytics.android.core.CrashlyticsCore
import com.facebook.stetho.Stetho
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import iftm.filipe.com.iftransparenciav2.app.AppComponent
import iftm.filipe.com.iftransparenciav2.app.DaggerAppComponent
import iftm.filipe.com.iftransparenciav2.service.APIClient
import io.fabric.sdk.android.Fabric
import javax.inject.Inject


class IFTransparenciaV2Application : Application(), HasActivityInjector {

    companion object {
        @get:Synchronized
        lateinit var instance: IFTransparenciaV2Application
        lateinit var appComponent: AppComponent

    }


    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

     var apiClient: APIClient = APIClient()

    override fun onCreate() {
        super.onCreate()
        instance = this

        initStetho()
        initDagger()
        val crashlyticsCore = CrashlyticsCore.Builder()
            .disabled(BuildConfig.DEBUG && BuildConfig.FLAVOR.contains("mock", true))
            .build()
        Fabric.with(this, Crashlytics.Builder().core(crashlyticsCore).build())

    }

    private fun initStetho() {
        Stetho.initializeWithDefaults(this)
    }

    private fun initDagger() {
        appComponent = DaggerAppComponent.builder()
                .application(this)
                .build()
          appComponent.inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingActivityInjector
}