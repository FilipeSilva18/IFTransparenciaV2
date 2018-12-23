package iftm.filipe.com.iftransparenciav2.app

import android.content.Context
import dagger.Module
import dagger.Provides
import iftm.filipe.com.iftransparenciav2.IFTransparenciaV2Application
import iftm.filipe.com.iftransparenciav2.data.repositories.AuxilioRepository
import iftm.filipe.com.iftransparenciav2.service.APIClient
import iftm.filipe.com.iftransparenciav2.service.RetrofitInitializer
import javax.inject.Singleton


@Module
class AppModule {

    @Provides
    @Singleton
    fun provideApplication() = IFTransparenciaV2Application.instance

    @Provides
    @Singleton
    fun provideContext(application: IFTransparenciaV2Application): Context = application.applicationContext

    @Provides
    @Singleton
    fun proviceAPIClient(application: IFTransparenciaV2Application): APIClient = application.apiClient


}