package co.com.post.di

import android.content.Context
import co.com.core.entities.Post
import co.com.core.entities.User
import co.com.core.use_cases.ICompletableUseCase
import co.com.core.use_cases.IUseCaseFactory
import co.com.core.use_cases.UseCaseFactory
import co.com.currencyexchange.core.use_cases.base.ISingleUseCase
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

/**
 * Created by oscarg798 on 3/20/18.
 */
@Singleton
@Module
class UsecaseModule {

    @Provides
    @Singleton
    fun providesUseCaseFactory(context: Context): IUseCaseFactory = UseCaseFactory(context)

    @Provides
    @Singleton
    fun providesGetPostsUseCase(useCaseFactory: IUseCaseFactory): ISingleUseCase<List<Post>, Boolean> {
        return useCaseFactory.getPostsSingleUseCase(Schedulers.io(), AndroidSchedulers.mainThread())
    }

    @Provides
    @Singleton
    fun providesGetUsersUseCase(useCaseFactory: IUseCaseFactory): ISingleUseCase<List<User>, Any?> {
        return useCaseFactory.getUsersUseCase(Schedulers.io(), AndroidSchedulers.mainThread())
    }

    @Provides
    @Singleton
    fun providesGetUserByIdUseCase(useCaseFactory: IUseCaseFactory): ISingleUseCase<User, Int> {
        return useCaseFactory.getUserByIdUseCase(Schedulers.io(), AndroidSchedulers.mainThread())
    }

    @Provides
    @Singleton
    fun provideMakePostFavoriteUseCase(useCaseFactory: IUseCaseFactory): ICompletableUseCase<Pair<Int, Boolean>>{
        return useCaseFactory.getMakePostFavoriteUseCase(Schedulers.io(), AndroidSchedulers.mainThread())
    }

}