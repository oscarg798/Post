package co.com.core.use_cases

import co.com.core.entities.Post
import io.reactivex.Scheduler

/**
 * Created by oscarg798 on 3/20/18.
 */
interface IUseCaseFactory {
    fun getPostsSingleUseCase(subscribeScheduler: Scheduler,
                              observerScheduler: Scheduler): SingleUseCase<List<Post>, Boolean>
}