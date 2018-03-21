package co.com.post.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by oscarg798 on 3/20/18.
 */
@Singleton
@Module
class AppModule(private val mApp: Application) {

    @Provides
    fun providesContext(): Context = mApp
}