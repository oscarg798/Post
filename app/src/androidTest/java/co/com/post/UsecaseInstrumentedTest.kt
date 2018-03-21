package co.com.post

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import co.com.core.entities.Post
import co.com.core.entities.User
import co.com.core.use_cases.ICompletableUseCase
import co.com.currencyexchange.core.use_cases.base.ISingleUseCase
import co.com.data.exceptions.PostNotFoundException
import co.com.post.di.AppModule
import co.com.post.di.UsecaseModule
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class UsecaseInstrumentedTest {


    @Inject
    lateinit var getPostUseCase: ISingleUseCase<List<Post>, Boolean>

    @Inject
    lateinit var getUsersUseCase: ISingleUseCase<List<User>, Any?>

    @Inject
    lateinit var makePostFavoriteUsecase: ICompletableUseCase<Pair<Int, Boolean>>

    @Before
    fun setUp() {
        val app = InstrumentationRegistry.getTargetContext().applicationContext as BaseApplication
        DaggerAppTestComponent.builder()
                .appModule(AppModule(app))
                .usecaseModule(UsecaseModule())
                .build()
                .inject(this)


    }


    @Test
    fun shouldGetPosts() {
        val observer = TestObserver<List<Post>>()
        getPostUseCase.getUseCase(true)
                .subscribe(observer)
        observer.assertNoErrors()
                .assertValueCount(1)
                .assertComplete()
    }

    @Test
    fun shouldGetUsers() {
        val observer = TestObserver<List<User>>()
        getUsersUseCase.getUseCase(null)
                .subscribe(observer)
        observer.assertNoErrors()
                .assertValueCount(1)
                .assertComplete()


    }

    @Test
    fun shouldFailMakingNoExistingPostFavorite() {
        val observer = TestObserver.create<Any?>()
        makePostFavoriteUsecase.getUseCase(Pair(504, true))
                .subscribeWith(observer)
        observer.assertError{
            it is PostNotFoundException
        }

    }


}
