package com.appdev.wordcheck.ui.view

import android.content.Intent
import android.util.Log
import com.appdev.wordcheck.R
import com.appdev.wordcheck.databinding.FragmentJustLookBinding
import com.appdev.wordcheck.ui.base.BaseFragment
import com.appdev.wordcheck.ui.viewmodel.WordViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class JustLookFragment : BaseFragment<FragmentJustLookBinding, WordViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_just_look

    override val viewModel: WordViewModel by viewModel<WordViewModel>()

    override fun initStartView() {
        initClickEvent()
        initGetList()
    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
    }

    private fun initGetList() {
        viewModel.getContentList()

    }

    private fun initClickEvent() {
// viewDataBinding.tbJust.setOnClickListener { onToolbarClick() }
        viewDataBinding.tbJust.setOnClickListener {
            Log.d("kkkk", viewModel.getContentListTaskEvent.value.toString())
        }

    }

    private fun onToolbarClick() {
        startActivity(Intent(activity, SearchActivity::class.java))
    }

}