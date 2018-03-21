package co.com.data.providers

import co.com.data.entities.APIPost
import co.com.data.entities.DBPost
import co.com.data.local.dao.PostDAO
import co.com.data.network.IPostRoute
import io.reactivex.Observable
import javax.inject.Inject


/**
 * Created by oscarg798 on 3/20/18.
 */
class PostRepository : IPostRepository {

    override var cachePosts: ArrayList<APIPost>? = null

    @Inject
    lateinit var mPostRoute: IPostRoute

    @Inject
    lateinit var mPostDAO: PostDAO

    override fun getPosts(shouldRefresh: Boolean): Observable<List<APIPost>> {
        if (shouldRefresh || cachePosts === null) {
            return mPostRoute.getPosts()
        }

        return Observable.just(cachePosts)
    }

    override fun savePosts(dbPosts: List<DBPost>) {
        dbPosts.forEach { mPostDAO.insert(it) }
    }

    override fun updatePost(dbPost: DBPost) {
        mPostDAO.update(dbPost)
    }

    override fun deletePosts() {
        mPostDAO.deleteAll()
    }

    override fun getPost(id: Int): DBPost? {
        return mPostDAO.get(id)
    }
}