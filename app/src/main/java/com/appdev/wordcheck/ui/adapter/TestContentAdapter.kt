package com.appdev.wordcheck.ui.adapter

import android.app.Activity
import android.app.Dialog
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.appdev.wordcheck.R
import com.appdev.wordcheck.data.model.domain.Content
import com.appdev.wordcheck.data.model.domain.Word
import com.appdev.wordcheck.databinding.ItemContentListBinding
import com.appdev.wordcheck.databinding.ItemTestContentBinding
import com.appdev.wordcheck.ui.viewmodel.WordViewModel
import com.appdev.wordcheck.util.EventObserver
import com.appdev.wordcheck.util.shortToast
import kotlinx.android.synthetic.main.popup_test.*

class TestAdapter(val activity: Activity, val viewModel: WordViewModel) :
    RecyclerView.Adapter<TestAdapter.ViewHolder>() {

    private var _data = mutableListOf<Content>()

    var data: List<Content> = _data
        set(value) {
            _data.clear()
            _data.addAll(value)
            notifyDataSetChanged()
        }

    inner class ViewHolder(private val binding: ItemTestContentBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun onBind(item: Content) {
            binding.data = item
            binding.btnConentContainer.setOnClickListener {
                val content = item.content
                onContainerClick(content)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemTestContentBinding =
            ItemTestContentBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(_data[position])

    }

    override fun getItemCount(): Int {
        return _data.size
    }

    private fun onContainerClick(content: String) {

        var wordList: List<Word>
        var i: Int = 0

        viewModel.getContentWordList(content)

        viewModel.getContentWordTaskEvent.observe(activity as LifecycleOwner, EventObserver {
            wordList = it.shuffled()
            val testDialog = Dialog(activity)
            testDialog.setContentView(R.layout.popup_test)
            testDialog.txt_spelling.text = wordList[i].spelling
            testDialog.img_next.setOnClickListener {
                if (i < wordList.size - 1) {
                    testDialog.txt_spelling.text = wordList[++i].spelling
                    testDialog.txt_answer.text = ""

                } else {
                    activity.shortToast("마지막 문제 입니다.")
                }
            }
            testDialog.img_previous.setOnClickListener {
                if (i > 0) {
                    testDialog.txt_spelling.text = wordList[--i].spelling
                    testDialog.txt_answer.text = ""

                } else {
                    activity.shortToast("처음 문제입니다.")

                }
            }
            testDialog.btn_look_answer.setOnClickListener {
                testDialog.txt_answer.text = wordList[i].meaning
            }
            testDialog.btn_correct.setOnClickListener {
                val id = wordList[i].id
                viewModel.scoreWord(id, "correct")

            }
            testDialog.btn_wrong.setOnClickListener {
                val id = wordList[i].id
                viewModel.scoreWord(id, "wrong")
                viewModel
            }
            testDialog.btn_close.setOnClickListener {
                testDialog.dismiss()
            }
            testDialog.show()
        })


    }

}