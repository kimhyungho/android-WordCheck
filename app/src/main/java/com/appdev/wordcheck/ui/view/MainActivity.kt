package com.appdev.wordcheck.ui.view

import android.content.Intent
import com.airbnb.lottie.animation.content.Content
import com.appdev.wordcheck.R
import com.appdev.wordcheck.databinding.ActivityMainBinding
import com.appdev.wordcheck.ui.adapter.ViewPagerAdapter
import com.appdev.wordcheck.ui.base.BaseActivity
import com.appdev.wordcheck.ui.viewmodel.WordViewModel
import com.appdev.wordcheck.util.EventObserver
import com.appdev.wordcheck.util.LoginPreference
import kotlin.properties.Delegates
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : BaseActivity<ActivityMainBinding, WordViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_main
    override val viewModel: WordViewModel by viewModel()

    lateinit var contentList: List<com.appdev.wordcheck.data.model.domain.Content>

    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun initStartView() {
        initClickEvent()
        initBottomNavigation()
        initViewPager()
    }

    override fun initBeforeBinding() {
    }

    override fun initAfterBinding() {
    }


    private fun initBottomNavigation() {
        viewDataBinding.bnvMain.setOnNavigationItemSelectedListener {
            var index by Delegates.notNull<Int>()
            when (it.itemId) {
                R.id.menu_just_look -> index = 0
                R.id.menu_test -> index = 1
                R.id.menu_add_word -> index = 2
                R.id.menu_bookmark -> index = 3
                R.id.menu_wrong_word -> index = 4
            }

            viewDataBinding.vpMain.currentItem = index
            true
        }
    }

    private fun initClickEvent() {
        viewDataBinding.tbMain.setNavigationOnClickListener { onClickLogout() }
        viewDataBinding.tbMain.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu_search -> onClickSearch()
            }

            true
        }
    }


    private fun initViewPager() {
        viewModel.getContentList()
        viewModel.getContentListTaskEvent.observe(this, EventObserver {
            viewPagerAdapter = ViewPagerAdapter(this)
            viewPagerAdapter.getContent(it as MutableList<com.appdev.wordcheck.data.model.domain.Content>)
            viewDataBinding.vpMain.adapter = viewPagerAdapter
            viewDataBinding.vpMain.isUserInputEnabled = false
        })
    }

    private fun onClickLogout() {
        LoginPreference.logout()
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    private fun onClickSearch() {
        startActivity(Intent(this, SearchActivity::class.java))
    }
}