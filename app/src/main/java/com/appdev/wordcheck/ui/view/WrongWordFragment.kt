package com.appdev.wordcheck.ui.view

import com.appdev.wordcheck.R
import com.appdev.wordcheck.databinding.FragmentAddWordBinding
import com.appdev.wordcheck.ui.base.BaseFragment
import com.appdev.wordcheck.ui.viewmodel.WordViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class WrongWordFragment : BaseFragment<FragmentAddWordBinding, WordViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_wrong_word

    override val viewModel: WordViewModel by viewModel<WordViewModel>()

    override fun initStartView() {
    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
    }
}