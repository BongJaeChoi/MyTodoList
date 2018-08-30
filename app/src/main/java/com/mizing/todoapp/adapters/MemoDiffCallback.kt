package com.mizing.todoapp.adapters

import android.support.v7.util.DiffUtil
import com.mizing.todoapp.model.MemoEntity

class MemoDiffCallback : DiffUtil.ItemCallback<MemoEntity>() {

    override fun areItemsTheSame(oldItem: MemoEntity, newItem: MemoEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MemoEntity, newItem: MemoEntity): Boolean {
        return oldItem == newItem
    }
}