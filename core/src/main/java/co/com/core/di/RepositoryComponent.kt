package co.com.core.di

import co.com.core.use_cases.post.DeletePostUseCase
import co.com.core.use_cases.post.GetPostSingleUseCase
import co.com.core.use_cases.post.MakePostFavoriteUseCase
import co.com.core.use_cases.user.GetUserByIdUseCase
import co.com.core.use_cases.user.GetUsersUseCase
import dagger.Component
import javax.inject.Singleton

/**
 * Created by oscarg798 on 3/20/18.
 */
@Singleton
@Component(modules = [(RepositoryModule::class)])
interface RepositoryComponent{

    fun inject(getPostSingleUseCase: GetPostSingleUseCase)

    fun inject(getUsersUseCase: GetUsersUseCase)

    fun inject(getUserByIdUseCase: GetUserByIdUseCase)

    fun inject(makePostFavoriteUseCase: MakePostFavoriteUseCase)

    fun inject(deletePostUseCase: DeletePostUseCase)
}