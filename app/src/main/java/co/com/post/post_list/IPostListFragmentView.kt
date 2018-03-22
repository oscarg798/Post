package co.com.post.post_list

import co.com.core.entities.Post
import co.com.post.IBaseView

/**
 * Created by oscarg798 on 3/21/18.
 */
interface IPostListFragmentView : IBaseView {

    fun showPosts(post: List<Post>)

    fun clear()

    fun updatePost(post: Post)

    fun deletePost(position: Int)

    fun getPostFromAdapter(position: Int): Post?

    fun showPost(post: Post)

    fun isDeviceOnLandscape(): Boolean

    fun getPost(): ArrayList<Post>?

}