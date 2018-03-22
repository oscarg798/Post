package co.com.post.post

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.com.core.entities.Post
import co.com.data.POST_KEY
import co.com.post.BaseApplication
import co.com.post.R
import kotlinx.android.synthetic.main.fragment_post.*


class PostFragment : Fragment(), IPostFragmentView {

    lateinit var mPresenter: IPostFragmentPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val presenter = PostFragmentPresenter()
        (activity?.applicationContext as BaseApplication).getAppComponent().inject(presenter)
        mPresenter = presenter
        lifecycle.addObserver(mPresenter)
        mPresenter.bind(this)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post, container, false)
    }

    override fun initComponents() {

    }

    override fun showProgressBar() {
        mTVAuthorName?.visibility = View.GONE
        mPBPostDetail?.visibility = View.VISIBLE

    }

    override fun hideProgressBar() {
        mTVAuthorName?.visibility = View.VISIBLE
        mPBPostDetail?.visibility = View.GONE
    }

    override fun showPostTitle(title: String) {
        mTVPostTitle?.text = title
    }


    override fun showPostBody(body: String) {
        mTVPostBody?.text = body

    }

    override fun showPostUserName(name: String) {
        mTVAuthorName?.text = name
    }

    override fun showPostUserEmail(userEmail: String) {
        mTVAuthorEmail?.text = userEmail
    }

    override fun getFragmentArguments(): Bundle? {
        return arguments
    }




    companion object {

        fun newInstance(post: Post): PostFragment {
            val fragment = PostFragment()
            val args = Bundle()
            args.putParcelable(POST_KEY, post)
            fragment.arguments = args
            return fragment
        }
    }
}
