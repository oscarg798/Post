package co.com.data

import co.com.data.di.NetworkComponent
import co.com.data.di.NetworkModule
import co.com.data.di.RouteModule
import co.com.data.entities.APIPost
import co.com.data.entities.APIUser
import co.com.data.network.IPostRoute
import co.com.data.network.IUserRoute
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.Schedulers
import it.cosenonjaviste.daggermock.DaggerMock
import it.cosenonjaviste.daggermock.InjectFromComponent
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class RoutesTest {

    @get:Rule
    val rule = DaggerMock.rule<NetworkComponent>(NetworkModule(),
            RouteModule())


    @InjectFromComponent
    lateinit var retrofit: Retrofit

    @InjectFromComponent
    lateinit var userRoute: IUserRoute

    @InjectFromComponent
    lateinit var postRoute: IPostRoute


    @Test
    fun shouldInjectRetrofit() {

        assertNotNull(retrofit)

    }

    @Test
    fun shouldInjectUserRoure() {
        assertNotNull(userRoute)
    }
    @Test
    fun shouldInjectPostRoute(){
        assertNotNull(postRoute)
    }



    @Test
    fun shouldGetUsersWithoutErrors() {
        val subscriber = TestObserver<List<APIUser>>()
        userRoute.getUsers()
                .subscribeWith(subscriber)

        subscriber.assertNoErrors()
                .assertComplete()
    }

    @Test
    fun shouldGet10Users() {
        userRoute.getUsers()
                .subscribeOn(Schedulers.trampoline())
                .observeOn(Schedulers.trampoline())
                .subscribe({ it ->
                    assertEquals(10, it.size)
                })
    }

    @Test
    fun shouldGetPostWithoutErrors() {
        val subscriber = TestObserver<List<APIPost>>()
        postRoute.getPosts()
                .subscribeWith(subscriber)

        subscriber.assertNoErrors()
                .assertComplete()
    }

    @Test
    fun shouldGet100Posts() {
        postRoute.getPosts()
                .subscribeOn(Schedulers.trampoline())
                .observeOn(Schedulers.trampoline())
                .subscribe({ it ->
                    assertEquals(100, it.size)
                })
    }
}