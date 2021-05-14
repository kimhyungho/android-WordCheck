package com.appdev.wordcheck.ui.view

import android.content.Intent
import com.appdev.wordcheck.R
import com.appdev.wordcheck.databinding.FragmentAddWordBinding
import com.appdev.wordcheck.databinding.FragmentWrongWordBinding
import com.appdev.wordcheck.ui.base.BaseFragment
import com.appdev.wordcheck.ui.viewmodel.WordViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class WrongWordFragment : BaseFragment<FragmentWrongWordBinding, WordViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_wrong_word

    override val viewModel: WordViewModel by viewModel<WordViewModel>()

    lateinit var intent: Intent

    override fun initStartView() {
        intent = Intent(activity, DetailContentActivity::class.java)
        initClickEvent()

    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
    }

    private fun initClickEvent() {
        viewDataBinding.wrongBtn1wrong.setOnClickListener {on1ButtonCLick()}
        viewDataBinding.wrongBtn2wrong.setOnClickListener {on2ButtonCLick()}
        viewDataBinding.wrongBtn3wrong.setOnClickListener {on3ButtonCLick()}
        viewDataBinding.wrongBtn4wrong.setOnClickListener {on4ButtonCLick()}
        viewDataBinding.wrongBtn5wrong.setOnClickListener {on5ButtonCLick()}
    }

    private fun on1ButtonCLick() {
        startWrongActivity(1)

    }

    private fun on2ButtonCLick() {
        startWrongActivity(2)
    }

    private fun on3ButtonCLick() {
        startWrongActivity(3)

    }

    private fun on4ButtonCLick() {
        startWrongActivity(4)

    }

    private fun on5ButtonCLick() {
        startWrongActivity(5)

    }

    private fun startWrongActivity(wrong_count: Int) {
        intent.putExtra("wrong_count", wrong_count)
        startActivity(intent)
    }
}