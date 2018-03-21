package co.com.core.di

import co.com.core.use_cases.post.GetPostSingleUseCase
import dagger.Component

/**
 * Created by oscarg798 on 3/20/18.
 */
@Component(modules = [(RepositoryModule::class)])
interface RepositoryComponent{

    fun inject(getPostSingleUseCase: GetPostSingleUseCase)
}