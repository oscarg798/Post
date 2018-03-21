package co.com.data.di

import co.com.data.providers.RepositoryFactory
import co.com.data.network.IPostRoute
import co.com.data.network.IUserRoute
import co.com.data.providers.PostRepository
import co.com.data.providers.UserRepository
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by oscarg798 on 3/20/18.
 */
@Singleton
@Component(modules = [(RouteModule::class)])
interface NetworkComponent {


    fun inject(postRepository: PostRepository)

    fun inject(userRepository: UserRepository)


    /**
     * we exposed some dependencies need it to unit testing
     */
    fun retrofit(): Retrofit

    fun userRoute(): IUserRoute

    fun postRoute(): IPostRoute
}