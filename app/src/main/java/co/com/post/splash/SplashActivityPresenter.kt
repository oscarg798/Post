package co.com.post.splash

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import co.com.core.entities.User
import co.com.currencyexchange.core.use_cases.base.ISingleUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

/**
 * Created by oscarg798 on 3/21/18.
 */
class SplashActivityPresenter : ISplashactivityPresenter {

    override var mView: ISplashActivityView? = null

    private val mDisposableBag = CompositeDisposable()

    @Inject
    lateinit var mGetUsersUseCase: ISingleUseCase<List<User>, Any?>

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun getUsers() {
        val disposable = object : DisposableSingleObserver<List<User>>() {
            override fun onSuccess(t: List<User>) {
                mView?.navigateToMainActivity()
                mDisposableBag.remove(this)
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
                mDisposableBag.remove(this)
            }
        }

        mDisposableBag.add(disposable)
        mGetUsersUseCase.execute(null, disposable)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private fun onStop() {

    }
}