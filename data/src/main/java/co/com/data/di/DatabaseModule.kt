package co.com.data.di

import android.arch.persistence.room.Room
import android.content.Context
import co.com.data.DATABASE_NAME
import co.com.data.local.AppDatabase
import co.com.data.local.dao.PostDAO
import co.com.data.local.dao.UserDAO
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by oscarg798 on 3/20/18.
 */
@Singleton
@Module
class DatabaseModule(private val mContext: Context) {


    @Provides
    fun providesContext(): Context = mContext


    @Provides
    fun providesDatabase(context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME).build()

    @Provides
    fun providesPostDAO(appDatabase: AppDatabase):PostDAO =
            appDatabase.postDAO()

    @Provides
    fun providesUserDAO(appDatabase: AppDatabase):UserDAO =
            appDatabase.userDAO()



}