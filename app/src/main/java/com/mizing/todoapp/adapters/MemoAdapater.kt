package com.mizing.todoapp.adapters

import android.arch.paging.PagedListAdapter
import android.content.Context
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.mizing.todoapp.model.MemoEntity

class MemoAdapater(val context: Context) : PagedListAdapter<MemoEntity, MemoAdapater.MemoViewHolder>(MemoDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): MemoViewHolder {


    }

    override fun onBindViewHolder(p0: MemoViewHolder, p1: Int) {
    }


    class MemoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}