package com.appdev.wordcheck.ui.view

import com.appdev.wordcheck.R
import com.appdev.wordcheck.databinding.ActivityDetailContentBinding
import com.appdev.wordcheck.ui.base.BaseActivity
import com.appdev.wordcheck.ui.viewmodel.WordViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailContentActivity : BaseActivity<ActivityDetailContentBinding, WordViewModel>() {
    override val layoutResourceId: Int = R.layout.activity_detail_content

    override val viewModel: WordViewModel by viewModel()

    override fun initStartView() {
        TODO("Not yet implemented")
    }

    override fun initBeforeBinding() {
        TODO("Not yet implemented")
    }

    override fun initAfterBinding() {
        TODO("Not yet implemented")
    }
}