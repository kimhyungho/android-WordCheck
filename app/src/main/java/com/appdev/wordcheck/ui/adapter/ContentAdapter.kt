package com.appdev.wordcheck.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.appdev.wordcheck.data.model.domain.Content
import com.appdev.wordcheck.databinding.ItemContentListBinding

class ContentAdapter() : RecyclerView.Adapter<ContentAdapter.ViewHolder>() {
    private var _data = mutableListOf<Content>()
    var data: List<Content> = _data
        set(value) {
            _data.clear()
            _data.addAll(value)
            notifyDataSetChanged()
        }

    inner class ViewHolder(private val binding: ItemContentListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: Content) {
            binding.content = item
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemContentListBinding =
            ItemContentListBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(_data[position])
        
    }

    override fun getItemCount(): Int {
        return _data.size
    }
}