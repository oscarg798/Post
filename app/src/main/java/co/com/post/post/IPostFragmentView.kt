package co.com.post.post

import android.os.Bundle
import co.com.post.IBaseView

/**
 * Created by oscarg798 on 3/21/18.
 */
interface IPostFragmentView : IBaseView {

    fun showPostTitle(title: String)

    fun showPostBody(body: String)

    fun showPostUserName(name: String)

    fun showPostUserEmail(userEmail: String)

    fun getFragmentArguments(): Bundle?

}