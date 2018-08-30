package com.mizing.todoapp.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.paging.DataSource
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.mizing.todoapp.model.AppDatabase
import com.mizing.todoapp.model.MemoEntity

class MemoViewModel(application: Application) : AndroidViewModel(application) {

    private var memosLiveData: LiveData<PagedList<MemoEntity>>

    init {
        val factory: DataSource.Factory<Int, MemoEntity> =
                AppDatabase.getInstance(application)
                        .memoDao()
                        .getPagedAllMemo()

        val config = PagedList.Config.Builder()
                .setInitialLoadSizeHint(20)
                .setPageSize(10)
                .setPrefetchDistance(5)
                .setEnablePlaceholders(true)
                .build()

        val livePageListBuilder: LivePagedListBuilder<Int, MemoEntity> = LivePagedListBuilder(factory, config)
        memosLiveData = livePageListBuilder.build()

    }

    fun memoList() = memosLiveData

}