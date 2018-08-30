package com.mizing.todoapp.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey

@Entity(
        tableName = "memo",
        indices = [Index("id")]
)
data class MemoEntity(
        @PrimaryKey(autoGenerate = true) var id: Long = 0,
        @ColumnInfo(name = "contents") var contents: String = "",
        @ColumnInfo(name = "created_at") var createdAt: Long = 0,
        @ColumnInfo(name = "image_url") var ImageUrl: String = "",
        @ColumnInfo(name = "update_at") var updateAt: Long = 0
)

