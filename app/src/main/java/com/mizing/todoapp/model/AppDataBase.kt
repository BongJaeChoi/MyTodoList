package com.mizing.todoapp.model

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.mizing.todoapp.Utils.DATABASE_NAME


@Database(entities = [MemoEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun memoDao(): MemoDAO

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }
        //개발자 문서는 싱글톤으로 작성할것을 강조하고있음
        //데이터베이스 클래스내 스레드 세이프 싱글톤 구현을 위해 Volatile, synchronized를 사용
        //object로 사용하여 뺄 수 있지만 argument 가 들어갔을때는 synchronized가 안전


        //sunflower 예제 참조
        // Create and pre-populate the database. See this article for more details:
        // https://medium.com/google-developers/7-pro-tips-for-room-fbadea4bfbd1#4785
        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            //데이터를 넣어놓으려면 미리 넣어놓는게 좋다
                        }
                    })
                    .build()
        }
    }
}