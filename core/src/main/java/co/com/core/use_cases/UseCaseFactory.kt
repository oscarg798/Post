package co.com.core.use_cases

import android.content.Context
import co.com.core.di.DaggerRepositoryComponent
import co.com.core.di.RepositoryModule
import co.com.core.entities.Post
import co.com.core.entities.User
import co.com.core.use_cases.post.DeletePostUseCase
import co.com.core.use_cases.post.GetPostSingleUseCase
import co.com.core.use_cases.post.MakePostFavoriteUseCase
import co.com.core.use_cases.user.GetUserByIdUseCase
import co.com.core.use_cases.user.GetUsersUseCase
import io.reactivex.Scheduler

/**
 * Created by oscarg798 on 3/20/18.
 */
class UseCaseFactory(mContext: Context) : IUseCaseFactory {

    private val mRepositoryComponent = DaggerRepositoryComponent.builder()
            .repositoryModule(RepositoryModule(mContext))
            .build()

    override fun getPostsSingleUseCase(subscribeScheduler: Scheduler,
                                       observerScheduler: Scheduler): SingleUseCase<List<Post>, Boolean> {

        val useCase = GetPostSingleUseCase(subscribeScheduler, observerScheduler)
        mRepositoryComponent?.inject(useCase)
        return useCase

    }

    override fun getUsersUseCase(subscribeScheduler: Scheduler, observerScheduler: Scheduler):
            SingleUseCase<List<User>, Any?> {
        val useCase = GetUsersUseCase(subscribeScheduler, observerScheduler)
        mRepositoryComponent?.inject(useCase)
        return useCase
    }

    override fun getUserByIdUseCase(subscribeScheduler: Scheduler, observerScheduler: Scheduler): SingleUseCase<User, Int> {
        val useCase = GetUserByIdUseCase(subscribeScheduler, observerScheduler)
        mRepositoryComponent?.inject(useCase)
        return useCase
    }

    override fun getMakePostFavoriteUseCase(subscribeScheduler: Scheduler, observerScheduler: Scheduler): CompletableUseCase<Pair<Int, Boolean>> {
        val useCase = MakePostFavoriteUseCase(subscribeScheduler, observerScheduler)
        mRepositoryComponent?.inject(useCase)
        return useCase
    }

    override fun getDeletePostUseCase(subscribeScheduler: Scheduler, observerScheduler: Scheduler): CompletableUseCase<Int> {
        val useCase = DeletePostUseCase(subscribeScheduler, observerScheduler)
        mRepositoryComponent?.inject(useCase)
        return useCase
    }
}