package com.appdev.wordcheck.ui.adapter

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.appdev.wordcheck.R
import com.appdev.wordcheck.data.model.domain.Content
import com.appdev.wordcheck.data.model.domain.Word
import com.appdev.wordcheck.databinding.ItemTestWrongBinding
import com.appdev.wordcheck.ui.viewmodel.WordViewModel
import com.appdev.wordcheck.util.EventObserver
import com.appdev.wordcheck.util.shortToast
import kotlinx.android.synthetic.main.popup_test.*

class TestWrongAdapter(val activity: Activity, val viewModel: WordViewModel) :
    RecyclerView.Adapter<TestWrongAdapter.ViewHolder>() {

    private var _data = mutableListOf<Content>()

    lateinit var testDialog: Dialog


    var data: List<Content> = _data
        set(value) {
            _data.clear()
            _data.addAll(value)
            notifyDataSetChanged()
        }

    inner class ViewHolder(private val binding: ItemTestWrongBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun onBind(item: Content, position: Int) {
            binding.data = item
            binding.btnConentContainer.setOnClickListener {
                val content = item.content
                onContainerClick(position + 1)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemTestWrongBinding =
            ItemTestWrongBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(_data[position], position)

    }

    override fun getItemCount(): Int {
        return _data.size
    }

    private fun onContainerClick(wrong_count: Int) {

        var wordList: List<Word>
        var i: Int = 0

        viewModel.getWrongWord(wrong_count)

        viewModel.getContentWordTaskEvent.observe(activity as LifecycleOwner, EventObserver {
            if (it.isNotEmpty()) {
                var i: Int = 0
                var correct = 0
                var wordList: List<Word> = it.shuffled()
                var myAnswer = ""
                var array =
                    activity.resources.getStringArray(R.array.noun).toCollection(ArrayList()).shuffled()
                        .toMutableList()
                val correctAnswer = wordList[i].meaning
                array.add(0, correctAnswer)
                var distinctArray = array.distinct()
                val randList = listOf<String>(
                    distinctArray[0],
                    distinctArray[1],
                    distinctArray[2],
                    distinctArray[3]
                ).shuffled()
                testDialog = Dialog(activity)
                testDialog.setContentView(R.layout.popup_test)
                testDialog.txt_spelling.text = wordList[i].spelling
                testDialog.rb_one.text = randList[0]
                testDialog.rb_two.text = randList[1]
                testDialog.rb_three.text = randList[2]
                testDialog.rb_four.text = randList[3]
                testDialog.btn_close.setOnClickListener { testDialog.dismiss() }
                testDialog.rb_one.setOnCheckedChangeListener { compoundButton, b ->
                    if (b) {
                        testDialog.btn_answer.isEnabled = true
                        myAnswer = testDialog.rb_one.text.toString()
                        testDialog.rb_two.isChecked = false
                        testDialog.rb_three.isChecked = false
                        testDialog.rb_four.isChecked = false
                    }
                }
                testDialog.rb_two.setOnCheckedChangeListener { compoundButton, b ->
                    if (b) {
                        testDialog.btn_answer.isEnabled = true
                        myAnswer = testDialog.rb_two.text.toString()
                        testDialog.rb_one.isChecked = false
                        testDialog.rb_three.isChecked = false
                        testDialog.rb_four.isChecked = false
                    }
                }
                testDialog.rb_three.setOnCheckedChangeListener { compoundButton, b ->
                    if (b) {
                        testDialog.btn_answer.isEnabled = true
                        myAnswer = testDialog.rb_three.text.toString()
                        testDialog.rb_one.isChecked = false
                        testDialog.rb_two.isChecked = false
                        testDialog.rb_four.isChecked = false
                    }
                }
                testDialog.rb_four.setOnCheckedChangeListener { compoundButton, b ->
                    if (b) {
                        testDialog.btn_answer.isEnabled = true
                        myAnswer = testDialog.rb_four.text.toString()
                        testDialog.rb_one.isChecked = false
                        testDialog.rb_two.isChecked = false
                        testDialog.rb_three.isChecked = false
                    }
                }
                testDialog.btn_answer.setOnClickListener {
                    testDialog.lav_img.visibility = View.VISIBLE
                    testDialog.btn_answer.isEnabled = false
                    testDialog.rb_one.isEnabled = false
                    testDialog.rb_two.isEnabled = false
                    testDialog.rb_three.isEnabled = false
                    testDialog.rb_four.isEnabled = false
                    testDialog.btn_next.isEnabled = true
                    if (myAnswer == wordList[i].meaning.toString()) {
                        testDialog.lav_img.frame = 0
                        testDialog.lav_img.setAnimation(R.raw.anim_correct)
                        testDialog.lav_img.resumeAnimation()
                        testDialog.txt_answer.text = wordList[i].meaning
                        testDialog.txt_test_category.text = wordList[i].category
                        testDialog.txt_answer.setTextColor(Color.parseColor("#00FF00"))
                        activity.shortToast("정답")
                        viewModel.scoreWord(wordList[i].id, "correct")
                        correct += 1
                    } else {
                        testDialog.lav_img.frame = 0
                        testDialog.lav_img.setAnimation(R.raw.anim_wrong)
                        testDialog.lav_img.resumeAnimation()
                        testDialog.txt_answer.text = wordList[i].meaning
                        testDialog.txt_test_category.text = wordList[i].category
                        testDialog.txt_answer.setTextColor(Color.parseColor("#FF0000"))
                        activity.shortToast("오답")
                        viewModel.scoreWord(wordList[i].id, "wrong")

                    }

                }
                testDialog.btn_next.setOnClickListener {
                    testDialog.lav_img.visibility = View.INVISIBLE
                    testDialog.btn_next.isEnabled = false

                    if (i < wordList.size - 1) {
                        var reList =
                            activity.resources.getStringArray(R.array.noun).toCollection(ArrayList())
                                .shuffled()
                                .toMutableList().shuffled().toMutableList()
                        reList.add(0, wordList[++i].meaning)
                        var distinctList = reList.distinct()
                        var randList = arrayListOf<String>(
                            distinctList[0],
                            distinctList[1],
                            distinctList[2],
                            distinctList[3]
                        ).shuffled()
                        testDialog.rb_one.text = randList[0]
                        testDialog.rb_two.text = randList[1]
                        testDialog.rb_three.text = randList[2]
                        testDialog.rb_four.text = randList[3]
                        testDialog.rb_one.isChecked = false
                        testDialog.rb_two.isChecked = false
                        testDialog.rb_three.isChecked = false
                        testDialog.rb_four.isChecked = false
                        testDialog.btn_answer.isEnabled = false
                        testDialog.rb_one.isEnabled = true
                        testDialog.rb_two.isEnabled = true
                        testDialog.rb_three.isEnabled = true
                        testDialog.rb_four.isEnabled = true
                        testDialog.txt_answer.text = ""
                        testDialog.txt_test_category.text = ""
                        testDialog.txt_spelling.text = wordList[i].spelling
                    } else {
                        val score = correct * 100 / wordList.size
                        testDialog.rb_one.visibility = View.INVISIBLE
                        testDialog.rb_two.visibility = View.INVISIBLE
                        testDialog.rb_three.visibility = View.INVISIBLE
                        testDialog.rb_four.visibility = View.INVISIBLE
                        testDialog.txt_answer.text =
                            "총 " + wordList.size.toString() + "문제 중 " + correct.toString() + "개 맞췄습니다."
                        testDialog.txt_test_category.text = "최종점수는"
                        testDialog.txt_spelling.text = score.toString() + "점"
                    }
                }
                testDialog.show()
            } else {
                activity.shortToast("문제가 존재하지 않습니다.")
            }

        })


    }

}