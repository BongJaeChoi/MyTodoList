package com.mizing.todoapp.model

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

/**
 * https://medium.com/androiddevelopers/7-pro-tips-for-room-fbadea4bfbd1#5e38
 * 참고하여 수정
 */
@Dao
abstract class MemoDAO : BaseDAO<MemoEntity> {
    //동기
    @Query("SELECT * FROM MEMO")
    abstract fun getAllMemoSync(): List<MemoEntity>

    @Query("SELECT * FROM MEMO WHERE id = :id")
    abstract fun getMemoByIdSync(id: Long): MemoEntity

    @Query("SELECT * FROM MEMO WHERE contents Like :search")
    abstract fun getMemoByContentSync(search: String): List<MemoEntity>

    //비동기
    //Observable 형태로 리턴할경우 Distinct 구현해야함
    @Query("SELECT * FROM MEMO")
    protected abstract fun getAllMemo(): LiveData<List<MemoEntity>>

    @Query("SELECT * FROM MEMO WHERE id = :id")
    protected abstract fun getMemoById(id: Long): LiveData<MemoEntity>

    @Query("SELECT * FROM MEMO WHERE contents Like :search")
    protected abstract fun getMemoByContent(search: String): LiveData<List<MemoEntity>>

    fun getDistinctAllMemo():LiveData<List<MemoEntity>> = getAllMemo().getDistinct()

    fun getMemoById():LiveData<MemoEntity> = getMemoById().getDistinct()

    fun getMemoByContent():LiveData<List<MemoEntity>> = getMemoByContent().getDistinct()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertMemo(memoEntity: MemoEntity): Long

    @Delete
    abstract fun deleteMemo(memoEntity: MemoEntity): Int

    @Update
    abstract fun updateMemo(memoEntity: MemoEntity): Int
}