package co.com.post

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import co.com.post.post_list.PostListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
                .replace(R.id.mFLMain, PostListFragment.newInstance(), null)
                .commitAllowingStateLoss()
    }


}
