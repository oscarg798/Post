package co.com.core.di

import android.content.Context
import co.com.data.providers.IPostRepository
import co.com.data.providers.IRepositoryFactory
import co.com.data.providers.IUserRepository
import co.com.data.providers.RepositoryFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by oscarg798 on 3/20/18.
 */
@Singleton
@Module
class RepositoryModule(private val mContext:Context) {

    @Provides
    fun providesRepositoryFactory(context: Context): IRepositoryFactory {
        return RepositoryFactory(context)
    }

    @Provides
    fun providesPostRepository(repositoryFactory: IRepositoryFactory): IPostRepository {
        return repositoryFactory.mPostRepository
    }

    @Provides
    fun providesUserRepository(repositoryFactory: IRepositoryFactory):IUserRepository{
        return repositoryFactory.mUserRepository
    }

    @Provides
    fun provideContext():Context = mContext


}