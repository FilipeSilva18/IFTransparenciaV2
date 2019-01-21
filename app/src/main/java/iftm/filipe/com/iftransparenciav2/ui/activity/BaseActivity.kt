package iftm.filipe.com.iftransparenciav2.ui.activity

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import br.com.zup.multistatelayout.MultiStateLayout
import com.google.firebase.auth.FirebaseAuth
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import javax.inject.Inject


abstract class BaseActivity<V : ViewDataBinding> : AppCompatActivity(), HasSupportFragmentInjector {

    lateinit var mViewDataBinding: V
    var multiStateLayout: MultiStateLayout? = null
    protected abstract fun getContentLayout(): Int

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        mViewDataBinding = DataBindingUtil.setContentView<V>(this, getContentLayout())
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentDispatchingAndroidInjector

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))
    }
}