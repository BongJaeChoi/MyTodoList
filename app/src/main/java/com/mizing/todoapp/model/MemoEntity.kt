package com.mizing.todoapp.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity(
        tableName = "memo",
        indices = [Index("id")]
)
data class MemoEntity(
        @PrimaryKey(autoGenerate = true) val id:Long,
        @ColumnInfo(name = "contents") val contents:String,
        @ColumnInfo(name = "created_at") val createdAt:Calendar = Calendar.getInstance(Locale.KOREA),
        @ColumnInfo(name = "image_url") val ImageUrl:String,
        @ColumnInfo(name = "update_at") val updateAt:Long
)

