package co.com.post.di

import co.com.post.post_list.PostListFragmentPresenter
import dagger.Component
import javax.inject.Singleton

/**
 * Created by oscarg798 on 3/20/18.
 */
@Singleton
@Component(modules = [(AppModule::class), (UsecaseModule::class)])
interface AppComponent {

    fun inject(postListFragmentPresenter: PostListFragmentPresenter)
}