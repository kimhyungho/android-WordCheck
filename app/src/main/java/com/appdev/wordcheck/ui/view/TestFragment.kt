package com.appdev.wordcheck.ui.view

import android.app.Dialog
import com.appdev.wordcheck.R
import com.appdev.wordcheck.databinding.FragmentAddWordBinding
import com.appdev.wordcheck.databinding.FragmentTestBinding
import com.appdev.wordcheck.ui.base.BaseFragment
import com.appdev.wordcheck.ui.viewmodel.WordViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class TestFragment : BaseFragment<FragmentTestBinding, WordViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_test

    override val viewModel: WordViewModel by viewModel<WordViewModel>()

    lateinit var testDialog: Dialog

    override fun initStartView() {
        initPopup()
        initClickEvent()
    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
    }

    private fun initClickEvent() {
        viewDataBinding.bntContentTest.setOnClickListener { showPopUp() }
    }

    private fun initPopup() {
        testDialog = Dialog(activity!!)
        testDialog.setContentView(R.layout.popup_test)

    }

    private fun showPopUp() {
        testDialog.show()
    }
}