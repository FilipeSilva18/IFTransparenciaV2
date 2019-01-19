package iftm.filipe.com.iftransparenciav2.di.builder

import dagger.Module
import dagger.android.ContributesAndroidInjector
import iftm.filipe.com.iftransparenciav2.ui.activity.MainActivity
import iftm.filipe.com.iftransparenciav2.di.ActivityScope
import iftm.filipe.com.iftransparenciav2.ui.activity.DetalhesAuxilioActivity
import iftm.filipe.com.iftransparenciav2.ui.activity.LoginActivity
import iftm.filipe.com.iftransparenciav2.ui.activity.RegisterActivity

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

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindRegisterActivity(): RegisterActivity


}