package com.appdev.wordcheck.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.appdev.wordcheck.data.model.domain.Word
import com.appdev.wordcheck.databinding.ItemWordListBinding

class WordAdapter() : RecyclerView.Adapter<WordAdapter.ViewHolder>() {
    private var _data = mutableListOf<Word>()
    var data: List<Word> = _data
        set(value) {
            _data.clear()
            _data.addAll(value)
            notifyDataSetChanged()
        }

    inner class ViewHolder(private val binding: ItemWordListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: Word) {
            binding.word = item
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemWordListBinding =
            ItemWordListBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(_data[position])

    }

    override fun getItemCount(): Int {
        return _data.size
    }
}