package iftm.filipe.com.iftransparenciav2.di.builder

import dagger.Module
import dagger.android.ContributesAndroidInjector
import iftm.filipe.com.iftransparenciav2.di.FragmentScope
import iftm.filipe.com.iftransparenciav2.ui.fragment.AssistenceSchollFragment

@Module
abstract class FragmentBuilder {

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun bindAssistenceSchollFragment(): AssistenceSchollFragment
}