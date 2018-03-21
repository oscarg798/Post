package co.com.data.providers

import co.com.data.entities.APIPost
import co.com.data.entities.DBPost
import io.reactivex.Observable

/**
 * Created by oscarg798 on 3/20/18.
 */
interface IPostRepository {

    var mCachePosts: List<APIPost>?

    fun getPosts(shouldRefresh: Boolean): Observable<List<APIPost>>

    fun getPost(id: Int): DBPost?

    fun savePosts(dbPosts: List<DBPost>)

    fun updatePost(dbPost: DBPost)

    fun deletePosts()

    fun getPostCount(): Int

    fun getPostFromDB(): List<DBPost>
}