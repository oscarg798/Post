package co.com.core.use_cases.post

import co.com.core.entities.Post
import co.com.core.entities.User
import co.com.core.use_cases.SingleUseCase
import co.com.data.entities.APIPost
import co.com.data.entities.DBPost
import co.com.data.providers.IPostRepository
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by oscarg798 on 3/20/18.
 */
class GetPostSingleUseCase(mSubscribeOnScheduler: Scheduler,
                           mObserverOnScheduler: Scheduler) :
        SingleUseCase<List<Post>, Boolean>(mSubscribeOnScheduler, mObserverOnScheduler) {

    @Inject
    lateinit var mPostRepository: IPostRepository


    override fun buildUseCase(params: Boolean): Single<List<Post>> {

        return Single.create<Int> {
            it.onSuccess(mPostRepository.getPostCount())
        }.flatMap {
            if (it == 0 || params) {
                Single.fromObservable(mPostRepository.getPosts(params))
            } else {
                Single.create<List<DBPost>> {
                    it.onSuccess(mPostRepository.getPostFromDB())
                }
            }
        }.map { posts ->

            val isAPiPost = posts.filterIsInstance(APIPost::class.java)
            val postList =
                    if (isAPiPost.isNotEmpty()) {
                        val dbPosts = isAPiPost.mapTo(ArrayList(), {
                            DBPost(it.id, it.userId, it.title, it.body, read = posts.indexOf(it) > 20)
                        })
                        mPostRepository.savePosts(dbPosts)
                        dbPosts
                    } else {
                        ArrayList(posts.filterIsInstance(DBPost::class.java))
                    }




            postList.map {

                val dbPost = mPostRepository.getPost(it.id)


                Post(it.id, it.userId,
                        it.title, it.body, dbPost?.favorite ?: false,
                        dbPost?.read ?: false)
            }


        }


    }
}