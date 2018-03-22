package co.com.core.use_cases.post

import co.com.core.use_cases.CompletableUseCase
import co.com.data.providers.IPostRepository
import io.reactivex.Completable
import io.reactivex.Scheduler
import javax.inject.Inject

/**
 * Created by oscarg798 on 3/21/18.
 */
class DeleteAllPostUseCase(mSubscribeOnScheduler: Scheduler,
                           mObserverOnScheduler: Scheduler) :
        CompletableUseCase<Any?>(mSubscribeOnScheduler, mObserverOnScheduler) {

    @Inject
    lateinit var mPostRepository: IPostRepository

    override fun buildUseCase(params: Any?): Completable {
        return Completable.create {
            mPostRepository.deletePosts()
            it.onComplete()
        }
    }
}