package com.appdev.wordcheck.ui.view

import android.app.Dialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.appdev.wordcheck.R
import com.appdev.wordcheck.databinding.FragmentTestBinding
import com.appdev.wordcheck.ui.adapter.TestAdapter
import com.appdev.wordcheck.ui.base.BaseFragment
import com.appdev.wordcheck.ui.viewmodel.WordViewModel
import com.appdev.wordcheck.util.EventObserver
import com.appdev.wordcheck.util.setupToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class TestFragment : BaseFragment<FragmentTestBinding, WordViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_test

    override val viewModel: WordViewModel by viewModel<WordViewModel>()

    lateinit var contentAdapter: TestAdapter

    override fun initStartView() {
        initRecyclerView()
        initClickEvent()
        activity!!.setupToast(this, viewModel.toastMessage)
    }

    override fun initDataBinding() {
        viewModel.getContentList()
    }

    override fun initAfterBinding() {
        observeContentList()
    }

    private fun initClickEvent() {
    }

    private fun observeContentList() {
        viewModel.getContentListTaskEvent.observe(this, EventObserver {
            contentAdapter.data = it
        })
    }

    private fun initRecyclerView() {
        contentAdapter = TestAdapter(activity!!, viewModel)
        viewDataBinding.rvContentTest.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = contentAdapter
        }


    }

}