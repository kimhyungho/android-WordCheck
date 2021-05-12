package com.appdev.wordcheck.ui.view

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.appdev.wordcheck.R
import com.appdev.wordcheck.databinding.FragmentJustLookBinding
import com.appdev.wordcheck.ui.adapter.ContentAdapter
import com.appdev.wordcheck.ui.base.BaseFragment
import com.appdev.wordcheck.ui.viewmodel.WordViewModel
import com.appdev.wordcheck.util.EventObserver
import com.appdev.wordcheck.util.setupToast
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.acos

class JustLookFragment : BaseFragment<FragmentJustLookBinding, WordViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_just_look

    override val viewModel: WordViewModel by viewModel<WordViewModel>()

    lateinit var contentAdapter : ContentAdapter

    override fun initStartView() {
        initClickEvent()
        initRecyclerView()
        activity!!.setupToast(this, viewModel.toastMessage)
    }

    override fun initDataBinding() {
        viewModel.getContentList()
    }

    override fun initAfterBinding() {
        observeContentList()
    }

    private fun observeContentList() {
        viewModel.getContentListTaskEvent.observe(this, EventObserver {
            contentAdapter.data = it
        })
    }

    private fun initRecyclerView() {
        contentAdapter = ContentAdapter(activity!!)

        viewDataBinding.rvJust.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = contentAdapter
            setHasFixedSize(true)
        }
    }

    private fun initClickEvent() {
        viewDataBinding.tbJust.setOnClickListener { onToolbarClick() }

    }

    private fun onToolbarClick() {
        startActivity(Intent(activity, SearchActivity::class.java))
    }

}