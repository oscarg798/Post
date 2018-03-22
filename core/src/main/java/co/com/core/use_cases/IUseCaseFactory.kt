package co.com.core.use_cases

import co.com.core.entities.Post
import co.com.core.entities.User
import io.reactivex.Scheduler

/**
 * Created by oscarg798 on 3/20/18.
 */
interface IUseCaseFactory {
    fun getPostsSingleUseCase(subscribeScheduler: Scheduler,
                              observerScheduler: Scheduler): SingleUseCase<List<Post>, Boolean>

    fun getUsersUseCase(subscribeScheduler: Scheduler,
                        observerScheduler: Scheduler): SingleUseCase<List<User>, Any?>

    fun getUserByIdUseCase(subscribeScheduler: Scheduler,
                           observerScheduler: Scheduler): SingleUseCase<User, Int>

    fun getMakePostFavoriteUseCase(subscribeScheduler: Scheduler,
                                   observerScheduler: Scheduler): CompletableUseCase<Pair<Int, Boolean>>


    fun getDeletePostUseCase(subscribeScheduler: Scheduler,
                             observerScheduler: Scheduler): CompletableUseCase<Int>
}