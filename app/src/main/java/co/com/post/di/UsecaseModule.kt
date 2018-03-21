package co.com.post.di

import android.content.Context
import co.com.core.entities.Post
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
    fun providesUseCaseFactory(context: Context): IUseCaseFactory = UseCaseFactory(context)

    @Provides
    fun providesGetPostsUseCase(useCaseFactory: IUseCaseFactory): ISingleUseCase<List<Post>, Boolean> {
        return useCaseFactory.getPostsSingleUseCase(Schedulers.io(), AndroidSchedulers.mainThread())
    }

}