package com.appdev.wordcheck.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.appdev.wordcheck.data.model.domain.Content
import com.appdev.wordcheck.ui.view.*

class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
//    private val fragmentList = mutableListOf<Fragment>()

    private var contentList = mutableListOf<Content>()

    fun getContent(content: MutableList<Content>) {
        contentList = content
    }

    override fun getItemCount(): Int {
        return 5
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> JustLookFragment(contentList)
            1 -> TestFragment()
            2 -> AddWordFragment()
            3 -> BookmarkFragment()
            4 -> WrongWordFragment()
            else -> JustLookFragment(contentList)
        }
    }
}