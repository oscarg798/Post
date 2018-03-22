package co.com.post.main

import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Menu
import android.view.MenuItem
import co.com.core.entities.Post
import co.com.post.IShowPostCallback
import co.com.post.R
import co.com.post.post.PostFragment
import co.com.post.post_list.PostListFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), IShowPostCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        changeFragment(PostListFragment.newInstance())


    }

    private fun changeFragment(fragment: Fragment, showDetail: Boolean = false) {
        val replaceFrameId = if (mFLSecondary !== null && showDetail) R.id.mFLSecondary else R.id.mFLMain
        supportFragmentManager.beginTransaction()
                .replace(replaceFrameId, fragment, null)
                .commitAllowingStateLoss()
    }

    override fun show(post: Post) {
        changeFragment(PostFragment.newInstance(post), true)

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        item?.let {
            supportFragmentManager.fragments.forEach {
                it.onOptionsItemSelected(item)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menu?.let {
            menuInflater.inflate(R.menu.main_activity_menu, it)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onBackPressed() {
        val fragmentList = supportFragmentManager.fragments
        if (fragmentList.isNotEmpty() &&
                resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT &&
                fragmentList[0] is PostFragment) {
            changeFragment(PostListFragment.newInstance())
        } else {
            super.onBackPressed()
        }
    }
}
