package co.com.core.use_cases

import android.content.Context
import co.com.core.di.DaggerRepositoryComponent
import co.com.core.di.RepositoryModule
import co.com.core.entities.Post
import co.com.core.use_cases.post.GetPostSingleUseCase
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
}