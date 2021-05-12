package com.appdev.wordcheck.ui.view

import androidx.recyclerview.widget.LinearLayoutManager
import com.appdev.wordcheck.R
import com.appdev.wordcheck.databinding.ActivityDetailContentBinding
import com.appdev.wordcheck.ui.adapter.WordAdapter
import com.appdev.wordcheck.ui.base.BaseActivity
import com.appdev.wordcheck.ui.viewmodel.WordViewModel
import com.appdev.wordcheck.util.EventObserver
import com.appdev.wordcheck.util.setupToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailContentActivity : BaseActivity<ActivityDetailContentBinding, WordViewModel>() {
    override val layoutResourceId: Int = R.layout.activity_detail_content

    override val viewModel: WordViewModel by viewModel()

    private val wordAdapter: WordAdapter = WordAdapter()

    override fun initStartView() {
        initRecyclerView()
        setupToast(this, viewModel.toastMessage)

    }

    override fun initBeforeBinding() {
        viewModel.getContentWordList("day1")
    }

    override fun initAfterBinding() {
        observeWordList()
    }

    private fun observeWordList() {
        viewModel.getContentWordTaskEvent.observe(this, EventObserver {
            wordAdapter.data = it
        })
    }

    private fun initRecyclerView() {
        viewDataBinding.rvDetail.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = wordAdapter
            setHasFixedSize(true)
        }
    }
}