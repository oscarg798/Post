package co.com.post

import co.com.core.entities.Post

/**
 * Created by oscarg798 on 3/21/18.
 */
interface IShowPostCallback {

    fun show(post: Post)
}