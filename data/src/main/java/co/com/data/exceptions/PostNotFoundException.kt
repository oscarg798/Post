package co.com.data.exceptions

/**
 * Created by oscarg798 on 3/21/18.
 */
class PostNotFoundException(val mPostId: Int) : Exception() {

    override val message: String?
        get() = "Sorry, Post $mPostId not found"
}