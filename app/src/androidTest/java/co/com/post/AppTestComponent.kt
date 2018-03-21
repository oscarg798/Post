package co.com.post


import co.com.post.di.AppModule
import co.com.post.di.UsecaseModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by oscarg798 on 3/20/18.
 */
@Singleton
@Component(modules = [(AppModule::class), (UsecaseModule::class)])
interface AppTestComponent {

    fun inject(usecaseInstrumentedTest: UsecaseInstrumentedTest)
}