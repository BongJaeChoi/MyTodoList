package com.mizing.todoapp.model

import android.arch.lifecycle.LiveData
import android.arch.paging.DataSource
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
    //UI에 바로 업데이트할거라면 pagingLibrary 사용
    @Query("SELECT * FROM MEMO")
    protected abstract fun getAllMemo(): DataSource.Factory<Int, MemoEntity>

    @Query("SELECT * FROM MEMO WHERE id = :id")
    protected abstract fun getMemoById(id: Long): LiveData<MemoEntity>

    @Query("SELECT * FROM MEMO WHERE contents Like :search")
    protected abstract fun getMemoByContent(search: String): DataSource.Factory<Int,MemoEntity>

    fun getPagedAllMemo(): DataSource.Factory<Int,MemoEntity> = getAllMemo()

    fun getDistinctMemoById(id: Long):LiveData<MemoEntity> = getMemoById(id).getDistinct()

    fun getPagedMemoByContent(search: String): DataSource.Factory<Int, MemoEntity> = getMemoByContent(search)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertMemo(memoEntity: MemoEntity): Long

    @Delete
    abstract fun deleteMemo(memoEntity: MemoEntity): Int

    @Update
    abstract fun updateMemo(memoEntity: MemoEntity): Int
}