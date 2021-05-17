package com.appdev.wordcheck.ui.view

import android.text.Editable
import android.text.TextWatcher
import androidx.recyclerview.widget.LinearLayoutManager
import com.appdev.wordcheck.R
import com.appdev.wordcheck.databinding.ActivitySearchBinding
import com.appdev.wordcheck.ui.adapter.WordAdapter
import com.appdev.wordcheck.ui.base.BaseActivity
import com.appdev.wordcheck.ui.viewmodel.WordViewModel
import com.appdev.wordcheck.util.EventObserver
import com.appdev.wordcheck.util.setupToast
import org.koin.androidx.viewmodel.ext.android.viewModel


class SearchActivity : BaseActivity<ActivitySearchBinding, WordViewModel>() {

    override val layoutResourceId: Int = R.layout.activity_search
    override val viewModel: WordViewModel by viewModel()

    private lateinit var wordAdapter: WordAdapter


    override fun initStartView() {
        initClickEvent()
        initRecyclerView()
        initPutTextEvent()
        setupToast(this, viewModel.toastMessage)
    }

    override fun initBeforeBinding() {
    }

    override fun initAfterBinding() {
        observeWordList()
    }

    private fun observeWordList() {
        viewModel.searchWordTaskEvent.observe(this, EventObserver {
            wordAdapter.data = it
        })
    }

    private fun initRecyclerView() {
        wordAdapter = WordAdapter(viewModel)
        viewDataBinding.rvSearch.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = wordAdapter
            setHasFixedSize(true)
        }
    }

    private fun initPutTextEvent() {
        viewDataBinding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val target = p0.toString()
                if (target.isNotEmpty()) {
                    viewModel.searchWord(p0.toString())
                }
            }
        })
    }

    private fun initClickEvent() {
        viewDataBinding.tbSearch.setNavigationOnClickListener { onBackButtonClick() }
    }

    private fun onBackButtonClick() {
        finish()
    }
}