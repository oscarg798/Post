package co.com.data.providers

import co.com.data.di.NetworkComponent

/**
 * Created by oscarg798 on 3/20/18.
 */
interface IRepositoryFactory {
    val mNetworkComponent: NetworkComponent?

    val mPostRepository: IPostRepository

    val mUserRepository: IUserRepository
}