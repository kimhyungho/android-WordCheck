package com.appdev.wordcheck.ui.view

import android.content.Intent
import android.view.MenuItem
import android.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.appdev.wordcheck.R
import com.appdev.wordcheck.databinding.ActivityMainBinding
import com.appdev.wordcheck.ui.adapter.ViewPagerAdapter
import com.appdev.wordcheck.ui.base.BaseActivity
import com.appdev.wordcheck.ui.base.BaseViewModel
import com.appdev.wordcheck.util.LoginPreference
import kotlin.properties.Delegates

class MainActivity : BaseActivity<ActivityMainBinding, BaseViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_main
    override val viewModel = BaseViewModel()

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
        viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        viewPagerAdapter.setFragmentList(
            listOf(
                JustLookFragment(),
                TestFragment(),
                AddWordFragment(),
                BookmarkFragment(),
                WrongWordFragment()
            )
        )
        viewDataBinding.vpMain.adapter = viewPagerAdapter
        viewDataBinding.vpMain.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                viewDataBinding.bnvMain.menu.getItem(position).isChecked = true
            }
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