package co.com.post

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import co.com.core.entities.Post
import co.com.currencyexchange.core.use_cases.base.ISingleUseCase
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


}
