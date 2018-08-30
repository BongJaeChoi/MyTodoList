package com.mizing.todoapp

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.mizing.todoapp.utils.DLog
import com.mizing.todoapp.model.AppDatabase
import com.mizing.todoapp.model.MemoDAO
import com.mizing.todoapp.model.MemoEntity
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * @link{https://www.bsidesoft.com/?p=6015} 링크를 참조해서 테스트 작성
 */
@RunWith(AndroidJUnit4::class)
class DBTest {
    private lateinit var db: AppDatabase
    private lateinit var memoDao: MemoDAO

    @Before
    fun before() {
        val appContext = InstrumentationRegistry.getTargetContext()
        db = Room.inMemoryDatabaseBuilder(appContext, AppDatabase::class.java).build()
        memoDao = db.memoDao()
    }

    @Test
    fun testSync() {
        DLog.i("동기테스트")
        var m1a = MemoEntity()
        var m1b: MemoEntity

        val m2a = MemoEntity()
        val m2b: MemoEntity

        var insertMemoId1: Long
        var insertMemoId2: Long

        //입력 테스트 1
        m1a.updateAt = 1111111
        m1a.contents = "테스트 1"
        m1a.createdAt = 222222
        m1a.ImageUrl = ""

        insertMemoId1 = memoDao.insertMemo(m1a)

        m1b = memoDao.getMemoByIdSync(insertMemoId1)
        assertNotNull(m1b)
        assertEquals(m1a.updateAt, m1b.updateAt)
        assertEquals(m1a.contents, m1b.contents)
        assertEquals(m1a.createdAt, m1b.createdAt)
        assertEquals(m1a.ImageUrl, m1b.ImageUrl)


        val memoByContentSync = memoDao.getMemoByContentSync("테스트")
        assertNotEquals(memoByContentSync.size,0)
        memoByContentSync.forEach {
            DLog.e(it.contents)
            assertEquals(it.contents.contains(m1a.contents), true)
        }


        //입력 테스트 1 끝

        //입력 테스트 2
        m2a.updateAt = 1111111
        m2a.contents = "테스트 2"
        m2a.createdAt = 222222
        m2a.ImageUrl = ""
        insertMemoId2 = memoDao.insertMemo(m2a)

        m2b = memoDao.getMemoByIdSync(insertMemoId2)
        assertNotNull(m2b)
        assertEquals(m2a.updateAt, m2b.updateAt)
        assertEquals(m2a.contents, m2b.contents)
        assertEquals(m2a.createdAt, m2b.createdAt)
        assertEquals(m2a.ImageUrl, m2b.ImageUrl)
        //입력 테스트 2 끝

        //입력된 리스트 사이즈
        var allMemoSync = memoDao.getAllMemoSync()
        assertNotNull(allMemoSync)
        assertEquals(2, allMemoSync.size)


        m1b.contents = "테스트 1 수정"
        var cnt = memoDao.updateMemo(m1b)//update 된 행 개수

        assertEquals(cnt, 1)

        m1a = memoDao.getMemoByIdSync(insertMemoId1)
        assertNotNull(m1a)
        assertEquals(m1a.contents, m1b.contents)

        cnt = memoDao.deleteMemo(m1a)
        assertEquals(1, cnt)

        m1a = memoDao.getMemoByIdSync(insertMemoId1)
        assertEquals(m1a, null)

        allMemoSync = memoDao.getAllMemoSync()
        assertEquals(allMemoSync.size,1)
        DLog.e("테스트 끝")

    }

    @After
    fun after() {
        db.close()
    }


}
