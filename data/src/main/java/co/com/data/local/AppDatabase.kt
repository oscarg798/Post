package co.com.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import co.com.data.local.dao.PostDAO
import co.com.data.local.dao.UserDAO
import co.com.data.entities.DBPost
import co.com.data.entities.DBUser

/**
 * Created by oscarg798 on 3/20/18.
 */
@Database(entities = [(DBPost::class),(DBUser::class)], version = 1)
abstract class AppDatabase:RoomDatabase(){

    abstract fun userDAO(): UserDAO

    abstract fun postDAO(): PostDAO
}