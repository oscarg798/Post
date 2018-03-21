package co.com.post.di

import co.com.core.di.RepositoryModule
import co.com.data.providers.IPostRepository
import dagger.Component

/**
 * Created by oscarg798 on 3/20/18.
 */
@Component(modules = [(AppModule::class), (UsecaseModule::class)])
interface AppComponent {

}