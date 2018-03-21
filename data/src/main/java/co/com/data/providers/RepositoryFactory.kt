package co.com.data.providers

import android.content.Context
import co.com.data.di.DaggerNetworkComponent
import co.com.data.di.DatabaseModule
import co.com.data.di.NetworkModule
import co.com.data.di.RouteModule

/**
 * Created by oscarg798 on 3/20/18.
 */
class RepositoryFactory(mContext: Context) : IRepositoryFactory {


    override val mNetworkComponent = DaggerNetworkComponent.builder()
            .networkModule(NetworkModule())
            .databaseModule(DatabaseModule(mContext))
            .routeModule(RouteModule())
            .build()


    override val mPostRepository: IPostRepository
        get() {
            val repo = PostRepository()
            mNetworkComponent?.inject(repo)
            return repo
        }

    override val mUserRepository: IUserRepository
        get() {
            val repo = UserRepository()
            mNetworkComponent?.inject(repo)
            return repo
        }

}