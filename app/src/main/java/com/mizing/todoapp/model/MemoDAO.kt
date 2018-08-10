package com.mizing.todoapp.model

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
interface MemoDAO {

    @Query("SELECT * FROM MEMO")
    fun getAllMemo(): LiveData<List<MemoEntity>>

    @Query("SELECT * FROM MEMO WHERE id = :id")
    fun getMemoById(id: Long) : LiveData<MemoEntity>

    @Query("SELECT * FROM MEMO WHERE contents Like :search")
    fun getMemoByContent(search: String) : LiveData<List<MemoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMemo()

}