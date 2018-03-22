package co.com.post.post

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import co.com.core.entities.Post
import co.com.core.entities.User
import co.com.currencyexchange.core.use_cases.base.ISingleUseCase
import co.com.data.POST_KEY
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

/**
 * Created by oscarg798 on 3/21/18.
 */
class PostFragmentPresenter : IPostFragmentPresenter {

    override var mView: IPostFragmentView? = null

    @Inject
    lateinit var mGetUserByIdUseCase: ISingleUseCase<User, Int>

    private val mDiposableBag = CompositeDisposable()


    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun getUserDetails() {
        val arguments = mView?.getFragmentArguments()
        if (arguments !== null && arguments.containsKey(POST_KEY)) {
            mView?.showProgressBar()
            val post = arguments.getParcelable(POST_KEY) as Post
            val disposable = object : DisposableSingleObserver<User>() {
                override fun onSuccess(t: User) {
                    mView?.showPostTitle(post.title)
                    mView?.showPostBody(post.body)
                    mView?.showPostUserName(t.name)
                    mView?.showPostUserEmail(t.email)
                    mView?.hideProgressBar()
                    mDiposableBag.remove(this)

                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    mView?.hideProgressBar()
                    mDiposableBag.remove(this)
                }
            }
            mDiposableBag.add(disposable)
            mGetUserByIdUseCase.execute(post.user, disposable)
        }

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private fun onStop() {
        mDiposableBag.clear()
    }


}