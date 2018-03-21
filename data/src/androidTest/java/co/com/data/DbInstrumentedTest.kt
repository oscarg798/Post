package co.com.data

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import co.com.data.entities.*
import co.com.data.local.AppDatabase
import co.com.data.local.dao.PostDAO
import co.com.data.local.dao.UserDAO
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
@RunWith(AndroidJUnit4::class)
class DbInstrumentedTest {

    lateinit var mDb: AppDatabase

    lateinit var mUserDao: UserDAO

    lateinit var mPostDAO: PostDAO


    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getTargetContext()
        mDb = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        mUserDao = mDb.userDAO()
        mPostDAO = mDb.postDAO()
    }

    @Test
    fun shouldInsertAPost() {
        val post = DBPost(1, 1, "sada", "asd")
        mPostDAO.insert(post)
        Assert.assertEquals(1, mPostDAO.get(1)!!.id)
    }

    @Test
    fun shouldUpdateAPost() {
        mPostDAO.insert(DBPost(1, 1, "sada", "asd"))
        val post = mPostDAO.getAll()[0]
        val newPost = DBPost(post.id, post.userId, "newPost", post.body)
        mPostDAO.update(newPost)
        Assert.assertEquals("newPost", mPostDAO.get(1)!!.title)

    }

    @Test
    fun shouldGetOneFavoritePost() {
        mPostDAO.insert(DBPost(1, 1, "sada", "asd", favorite = true))
        mPostDAO.insert(DBPost(2, 1, "no", "no"))
        Assert.assertEquals(1, mPostDAO.getFavorites().size)
    }


    @Test
    fun shouldInsertAnUser() {
        val dbUser = DBUser(1, "Oscar Gallon", "oscarg798",
                "oscarg798@gmail.com", "12312312", "www.a.com",
                DBCompany("K", "Hello", "You"),
                DBAddress("calle 67", "501", "Itagui",
                        "1213213", DBGeo(12213.0, 123123.0)))
        mUserDao.insert(dbUser)
        Assert.assertEquals(mUserDao.getAll().size, 1)
    }

    @Test
    fun shouldDeleteAll(){
        mPostDAO.insert(DBPost(1, 1, "sada", "asd", favorite = true))
        mPostDAO.insert(DBPost(2, 1, "no", "no"))
        mPostDAO.deleteAll()
        Assert.assertEquals(0, mPostDAO.getAll().size)
    }

    @After
    fun closeDb() {
        mDb.close()
    }
}


