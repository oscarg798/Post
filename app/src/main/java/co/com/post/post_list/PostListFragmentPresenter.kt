package co.com.post.post_list

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import android.support.v7.widget.RecyclerView
import co.com.core.entities.Post
import co.com.core.use_cases.ICompletableUseCase
import co.com.currencyexchange.core.use_cases.base.ISingleUseCase
import co.com.post.R
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

/**
 * Created by oscarg798 on 3/21/18.
 */
class PostListFragmentPresenter : IPostListFragmentPresenter {


    override var mView: IPostListFragmentView? = null

    private val mDisposableBag = CompositeDisposable()

    @Inject
    lateinit var mGetPostUseCase: ISingleUseCase<List<Post>, Boolean>

    @Inject
    lateinit var mMakePostFavoriteUseCase: ICompletableUseCase<Pair<Int, Boolean>>

    @Inject
    lateinit var mDeletePostUseCase: ICompletableUseCase<Int>

    @Inject
    lateinit var mDeleteAllPostUseCase: ICompletableUseCase<Any?>

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun onResume() {
        getPost(false)

    }

    private fun getPost(useAPI: Boolean) {
        mView?.showProgressBar()

        val disposable = object : DisposableSingleObserver<List<Post>>() {
            override fun onSuccess(t: List<Post>) {
                val isLandscape = mView?.isDeviceOnLandscape() ?: false
                if (isLandscape && t.isNotEmpty()) {
                    mView?.showPost(t[0])
                }
                mView?.showPosts(t)
                mView?.hideProgressBar()
                mDisposableBag.remove(this)
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
                mView?.hideProgressBar()
                mDisposableBag.remove(this)
            }
        }

        mDisposableBag.add(disposable)
        mGetPostUseCase.execute(useAPI, disposable)
    }

    override fun postSelected(post: Post) {
        mView?.showPost(post)
    }


    override fun onSwipe(viewHolder: RecyclerView.ViewHolder?, direction: Int) {
        viewHolder?.let { holder ->

            val post = mView?.getPostFromAdapter(holder.adapterPosition)

            post?.let {
                val disposable = object : DisposableCompletableObserver() {
                    override fun onComplete() {
                        mView?.deletePost(holder.adapterPosition)
                        mDisposableBag.remove(this)
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        mDisposableBag.remove(this)
                    }
                }
                mDisposableBag.add(disposable)
                mDeletePostUseCase.execute(it.id, disposable)
            }


        }
    }

    override fun onMenuItemSelected(itemId: Int) {
        when (itemId) {
            R.id.navigation_refresh -> {
                getPost(true)
            }
            else -> {
                deleteAllPost()
            }
        }
    }

    private fun deleteAllPost() {
        mView?.showProgressBar()
        val disposable = object : DisposableCompletableObserver() {
            override fun onComplete() {
                mView?.clear()
                mView?.hideProgressBar()
                mDisposableBag.remove(this)
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
                mView?.hideProgressBar()
                mDisposableBag.remove(this)
            }
        }
        mDisposableBag.add(disposable)
        mDeleteAllPostUseCase.execute(null, disposable)
    }


    override fun favoriteButtonClick(post: Post) {
        val disposable = object : DisposableCompletableObserver() {
            override fun onComplete() {
                mView?.updatePost(Post(post.id, post.user, post.title, post.body,
                        !post.favorite, post.read))
                mView?.hideProgressBar()
                mDisposableBag.remove(this)
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
                mView?.hideProgressBar()
                mDisposableBag.remove(this)
            }
        }
        mDisposableBag.add(disposable)
        mMakePostFavoriteUseCase.execute(Pair(post.id, !post.favorite), disposable)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private fun onStop() {
        mDisposableBag.clear()
    }
}