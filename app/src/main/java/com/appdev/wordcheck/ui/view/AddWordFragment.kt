package com.appdev.wordcheck.ui.view

import com.appdev.wordcheck.R
import com.appdev.wordcheck.databinding.FragmentAddWordBinding
import com.appdev.wordcheck.ui.base.BaseFragment
import com.appdev.wordcheck.ui.viewmodel.WordViewModel
import com.appdev.wordcheck.util.setupToast
import org.koin.androidx.viewmodel.ext.android.viewModel


class AddWordFragment : BaseFragment<FragmentAddWordBinding, WordViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_add_word

    override val viewModel: WordViewModel by viewModel<WordViewModel>()

    override fun initStartView() {
        initClickEvent()
        activity!!.setupToast(this, viewModel.toastMessage)
    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
    }

    private fun initClickEvent() {
        viewDataBinding.btnAddWord.setOnClickListener {
            onAddButtonClick()
        }
    }

    private fun onAddButtonClick() {
        val contents = viewDataBinding.etContents.text.toString()
        val spelling = viewDataBinding.etSpelling.text.toString()
        val meaning = viewDataBinding.etMeaning.text.toString()
        val category = viewDataBinding.spCategory.selectedItem.toString()
        viewModel.addWord(contents, spelling, category, meaning)
    }
}