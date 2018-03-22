package co.com.core.use_cases.post

import co.com.core.use_cases.CompletableUseCase
import co.com.data.exceptions.PostNotFoundException
import co.com.data.providers.IPostRepository
import io.reactivex.Completable
import io.reactivex.Scheduler
import javax.inject.Inject

/**
 * Created by oscarg798 on 3/21/18.
 */
class DeletePostUseCase(mSubcribeOnScheduler: Scheduler,
                        mobserverOnScheduler: Scheduler) :
        CompletableUseCase<Int>(mSubcribeOnScheduler, mobserverOnScheduler) {


    @Inject
    lateinit var mPostRepository: IPostRepository

    override fun buildUseCase(params: Int): Completable {
        return Completable.create {

            val dbPost = mPostRepository.getPost(params)
            if (dbPost !== null) {
                mPostRepository.deletePost(dbPost)
                it.onComplete()
            } else {
                it.onError(PostNotFoundException(params))
            }

        }
    }
}