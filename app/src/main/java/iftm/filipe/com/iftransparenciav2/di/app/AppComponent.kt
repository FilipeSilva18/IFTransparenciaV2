package iftm.filipe.com.iftransparenciav2.app

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import iftm.filipe.com.iftransparenciav2.IFTransparenciaV2Application
import iftm.filipe.com.iftransparenciav2.di.builder.ActivityBuilder
import iftm.filipe.com.iftransparenciav2.di.builder.FragmentBuilder
import iftm.filipe.com.iftransparenciav2.di.builder.ViewModelBuilder
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class,
        AndroidSupportInjectionModule::class,
        AndroidInjectionModule::class,
        ActivityBuilder::class,
        FragmentBuilder::class,
        ViewModelBuilder::class))

interface AppComponent {


    fun inject(application: IFTransparenciaV2Application)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}