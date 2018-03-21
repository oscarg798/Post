package co.com.post

import android.app.Application
import co.com.post.di.AppComponent
import co.com.post.di.AppModule
import co.com.post.di.DaggerAppComponent
import co.com.post.di.UsecaseModule

/**
 * Created by oscarg798 on 3/20/18.
 */
class BaseApplication : Application() {


    lateinit var mDaggerAppComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        mDaggerAppComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .usecaseModule(UsecaseModule())
                .build()
    }

    fun getAppComponent() = mDaggerAppComponent

}