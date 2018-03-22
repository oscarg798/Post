package co.com.post

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import co.com.core.entities.Post
import co.com.post.post.PostFragment
import co.com.post.post_list.PostListFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), IShowPostCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
                .replace(R.id.mFLMain, PostListFragment.newInstance(), null)
                .commitAllowingStateLoss()


    }

    override fun show(post: Post) {
        val replaceFrameId = if (mFLSecondary !== null) R.id.mFLSecondary else R.id.mFLMain
        supportFragmentManager.beginTransaction()
                .replace(replaceFrameId, PostFragment.newInstance(post), null)
                .commitAllowingStateLoss()

    }
}
