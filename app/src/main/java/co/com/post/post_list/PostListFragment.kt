package co.com.post.post_list

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.com.core.entities.Post
import co.com.post.BaseApplication
import co.com.post.IShowPostCallback
import co.com.post.R
import kotlinx.android.synthetic.main.fragment_post_list.*

class PostListFragment : Fragment(), IPostListFragmentView {


    lateinit var mPresenter: IPostListFragmentPresenter


    override fun onAttach(context: Context?) {
        super.onAttach(context)


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val presenter = PostListFragmentPresenter()
        (activity?.applicationContext as BaseApplication).getAppComponent().inject(presenter)
        mPresenter = presenter
        lifecycle.addObserver(mPresenter)
        mPresenter.bind(this)


    }

    override fun showPost(post: Post) {
        (activity as? IShowPostCallback)?.show(post)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initComponents()
    }

    override fun initComponents() {
        mRVPost?.layoutManager = LinearLayoutManager(activity)
        mRVPost?.setHasFixedSize(false)
        mRVPost?.adapter = PostRVAdapter(mCallbacks = mPresenter)
        mRVPost?.adapter?.let { adapter ->
            activity?.let {
                val swipeHandler = object : SwipeToDeleteCallback(it) {
                    override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {
                        mPresenter.onSwipe(viewHolder, direction)
                    }


                }
                val itemTouchHelper = ItemTouchHelper(swipeHandler)
                itemTouchHelper.attachToRecyclerView(mRVPost)
            }
        }


    }

    override fun isDeviceOnLandscape(): Boolean {
        activity?.windowManager?.let {
            return activity!!.resources.configuration.orientation != Configuration.ORIENTATION_PORTRAIT
        }

        return false
    }

    override fun showProgressBar() {
        mPBPost?.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        mPBPost?.visibility = View.GONE
    }

    override fun clear() {
        (mRVPost?.adapter as? PostRVAdapter)?.clear()
    }

    override fun showPosts(post: List<Post>) {
        (mRVPost?.adapter as? PostRVAdapter)?.clear()
        (mRVPost?.adapter as? PostRVAdapter)?.add(post)
    }

    override fun updatePost(post: Post) {
        (mRVPost?.adapter as? PostRVAdapter)?.update(post)
    }

    override fun deletePost(position: Int) {
        (mRVPost?.adapter as? PostRVAdapter)?.delete(position)
    }

    override fun getPostFromAdapter(position: Int): Post? {
        return (mRVPost?.adapter as? PostRVAdapter)?.getPost(position)
    }

    companion object {

        fun newInstance(): PostListFragment {
            return PostListFragment()
        }
    }
}// Required empty public constructor
