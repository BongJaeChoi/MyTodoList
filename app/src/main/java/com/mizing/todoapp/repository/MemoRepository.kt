package com.mizing.todoapp.repository

import com.mizing.todoapp.model.MemoDAO

class MemoRepository private constructor(
        private val memoDAO: MemoDAO
)  {
    fun getMemoList() = memoDAO.getPagedAllMemo()

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: MemoRepository? = null

        fun getInstance(memoDAO: MemoDAO) =
                instance ?: synchronized(this) {
                    instance ?: MemoRepository(memoDAO).also { instance = it }
                }
    }

}