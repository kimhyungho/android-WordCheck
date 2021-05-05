package com.appdev.wordcheck.ui.view

import com.appdev.wordcheck.R
import com.appdev.wordcheck.databinding.ActivitySignUpBinding
import com.appdev.wordcheck.ui.base.BaseActivity
import com.appdev.wordcheck.ui.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivitySignUpBinding, UserViewModel>() {
    override val layoutResourceId: Int = R.layout.activity_main
    override val viewModel: UserViewModel by viewModel()

    override fun initStartView() {
    }

    override fun initBeforeBinding() {
    }

    override fun initAfterBinding() {
    }
}