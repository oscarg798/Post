package co.com.post.post_list

import co.com.core.entities.Post

/**
 * Created by oscarg798 on 3/21/18.
 */
interface PostListCallbacks {


    fun postSelected(post: Post)

    fun favoriteButtonClick(post: Post)
}