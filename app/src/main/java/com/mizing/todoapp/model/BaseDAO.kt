package com.mizing.todoapp.model

import android.arch.persistence.room.*

@Dao
interface BaseDAO<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: T): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg obj: T): Long

    @Delete
    fun delete(obj: T): Int

    @Update
    fun update(obj: T): Int
}