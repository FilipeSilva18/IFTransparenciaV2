package iftm.filipe.com.iftransparenciav2.ui.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import iftm.filipe.com.iftransparenciav2.R
import iftm.filipe.com.iftransparenciav2.databinding.ActivityMainBinding
import iftm.filipe.com.iftransparenciav2.ui.fragment.AssistenceSchollFragment
import iftm.filipe.com.iftransparenciav2.ui.viewmodel.MainViewModel
import javax.inject.Inject


class MainActivity : BaseActivity<ActivityMainBinding>() {


    @Inject
    lateinit var mViewModel: MainViewModel


    override fun getContentLayout(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewDataBinding.viewModel = mViewModel

        mViewDataBinding.bnvMain.setOnNavigationItemSelectedListener { menuItem ->
            var fragment: Fragment

            when (menuItem.itemId) {
                R.id.menu_feed -> {
                    fragment = AssistenceSchollFragment()
                }
                R.id.menu_zuppers -> {
                    fragment = AssistenceSchollFragment()
                }
                R.id.menu_info -> {
                    fragment = AssistenceSchollFragment()
                }
                R.id.menu_profile -> {
                    fragment = AssistenceSchollFragment()
                }
                else -> {
                    fragment = AssistenceSchollFragment()
                }


            }

            supportFragmentManager.beginTransaction()
                    .replace(R.id.fl_container, fragment)
                    .commit()

            return@setOnNavigationItemSelectedListener true
        }
        mViewDataBinding.bnvMain.selectedItemId = R.id.menu_feed
    }


}



