package com.tangxb.learnkotlin.ui

import android.support.v4.app.FragmentTransaction
import com.tangxb.learnkotlin.R
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.Logger.addLogAdapter



class MainActivity : BaseActivity() {
    override fun getLayoutResId() = R.layout.activity_main

    lateinit var transaction: FragmentTransaction
    lateinit var homeFragment: HomeFragment

    override fun initData() {
        Logger.addLogAdapter(AndroidLogAdapter())

        transaction = supportFragmentManager.beginTransaction()
        if (supportFragmentManager.findFragmentByTag(HomeFragment.TAG) == null) {
            homeFragment = HomeFragment.getInstance()
            transaction.add(R.id.viewpager, homeFragment, HomeFragment.TAG)
        } else {
            homeFragment = supportFragmentManager.findFragmentByTag(HomeFragment.TAG) as HomeFragment
        }
        transaction.show(homeFragment)
        transaction.commit()
    }


}
