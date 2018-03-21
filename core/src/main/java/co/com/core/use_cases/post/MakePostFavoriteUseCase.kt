package co.com.core.use_cases.post

import co.com.core.use_cases.CompletableUseCase
import co.com.data.entities.DBPost
import co.com.data.exceptions.PostNotFoundException
import co.com.data.providers.IPostRepository
import io.reactivex.Completable
import io.reactivex.Scheduler
import javax.inject.Inject

/**
 * Created by oscarg798 on 3/21/18.
 */
class MakePostFavoriteUseCase(mSubscribeOnScheduler: Scheduler,
                              mObserverOnScheduler: Scheduler) :
        CompletableUseCase<Pair<Int, Boolean>>(mSubscribeOnScheduler, mObserverOnScheduler) {

    @Inject
    lateinit var mPostRepository: IPostRepository

    override fun buildUseCase(params: Pair<Int, Boolean>): Completable {
        return Completable.create {
            val dbPost = mPostRepository.getPost(params.first)
            if(dbPost!==null){
                mPostRepository.updatePost(DBPost(dbPost.id, dbPost.userId, dbPost.title,
                        dbPost.body, params.second))
                it.onComplete()

            }else{
                it.onError(PostNotFoundException(params.first))
            }


        }
    }
}