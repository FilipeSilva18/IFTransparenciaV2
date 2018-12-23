package iftm.filipe.com.iftransparenciav2.di.builder

import dagger.Module
import dagger.android.ContributesAndroidInjector
import iftm.filipe.com.iftransparenciav2.ui.activity.MainActivity
import iftm.filipe.com.iftransparenciav2.di.ActivityScope
import iftm.filipe.com.iftransparenciav2.ui.activity.DetalhesAuxilioActivity
import iftm.filipe.com.iftransparenciav2.ui.activity.LoginActivity

@Module
abstract class ActivityBuilder {

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindDetalhesAuxilioActivity(): DetalhesAuxilioActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindLoginActivity(): LoginActivity


}