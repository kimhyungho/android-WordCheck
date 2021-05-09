package com.appdev.wordcheck.ui.view

import androidx.viewpager.widget.ViewPager
import com.appdev.wordcheck.R
import com.appdev.wordcheck.databinding.ActivityMainBinding
import com.appdev.wordcheck.ui.adapter.ViewPagerAdapter
import com.appdev.wordcheck.ui.base.BaseActivity
import com.appdev.wordcheck.ui.base.BaseViewModel
import kotlin.properties.Delegates

class MainActivity : BaseActivity<ActivityMainBinding, BaseViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_main
    override val viewModel = BaseViewModel()

    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun initStartView() {
        initBottomNavigation()
        initViewPager()
    }

    override fun initBeforeBinding() {
    }

    override fun initAfterBinding() {
    }

    private fun initBottomNavigation() {
        viewDataBinding.bnvMain.setOnNavigationItemReselectedListener {
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
            ) {}

            override fun onPageSelected(position: Int) {
                viewDataBinding.bnvMain.menu.getItem(position).isChecked = true
            }
        })
    }
}