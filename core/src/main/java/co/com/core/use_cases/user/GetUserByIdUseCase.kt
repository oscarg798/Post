package co.com.core.use_cases.user

import co.com.core.entities.User
import co.com.core.use_cases.SingleUseCase
import co.com.data.providers.IUserRepository
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by oscarg798 on 3/21/18.
 */
class GetUserByIdUseCase(mSubscribeScheduler: Scheduler,
                         mObserverScheduler: Scheduler) :
        SingleUseCase<User, Int>(mSubscribeScheduler, mObserverScheduler) {
    @Inject
    lateinit var mUserRepository: IUserRepository


    override fun buildUseCase(params: Int): Single<User> {
        return Single.create { emitter ->

            val dbUser = mUserRepository.getUser(params)
            emitter.onSuccess(User(dbUser.id, dbUser.name, dbUser.username, dbUser.email, dbUser.phone,
                    dbUser.website, dbUser.company.name,
                    "${dbUser.address.city}," +
                            "${dbUser.address.street},${dbUser.address.suite}"))
        }
    }

}