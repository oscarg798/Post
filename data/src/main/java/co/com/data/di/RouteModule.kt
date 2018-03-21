package co.com.data.di

import co.com.data.network.IPostRoute
import co.com.data.network.IUserRoute
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by oscarg798 on 3/20/18.
 */
@Singleton
@Module(includes = [(NetworkModule::class), (DatabaseModule::class)])
class RouteModule {

    @Provides
    fun providesPostRoute(retrofit: Retrofit): IPostRoute = retrofit.create(IPostRoute::class.java)

    @Provides
    fun providesUserRoute(retrofit: Retrofit): IUserRoute = retrofit.create(IUserRoute::class.java)
}