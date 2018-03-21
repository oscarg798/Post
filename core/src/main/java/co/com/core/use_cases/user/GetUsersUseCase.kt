package co.com.core.use_cases.user

import co.com.core.entities.User
import co.com.core.use_cases.SingleUseCase
import co.com.data.entities.DBAddress
import co.com.data.entities.DBCompany
import co.com.data.entities.DBGeo
import co.com.data.entities.DBUser
import co.com.data.providers.IUserRepository
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by oscarg798 on 3/21/18.
 */
class GetUsersUseCase(mSubscribeOnScheduler: Scheduler,
                      mObserverOnScheduler: Scheduler) :
        SingleUseCase<List<User>, Any?>(mSubscribeOnScheduler, mObserverOnScheduler) {

    @Inject
    lateinit var mUserRepository: IUserRepository

    override fun buildUseCase(params: Any?): Single<List<User>> {
        return Single.fromObservable(mUserRepository.getUsers())
                .map {
                    it.map {
                        DBUser(it.id, it.name, it.username, it.email, it.phone,
                                it.website, DBCompany(it.company.name,
                                it.company.catchPhrase, it.company.bs),
                                DBAddress(it.address.street, it.address.suite, it.address.city,
                                        it.address.zipCode, DBGeo(it.address.geo.lat,
                                        it.address.geo.lng)))
                    }.forEach {
                        mUserRepository.insert(it)
                    }

                    it.mapTo(ArrayList(), {
                        User(it.id, it.name, it.username, it.email, it.phone,
                                it.website, it.company.name,
                                "${it.address.city}," +
                                        "${it.address.street},${it.address.suite}")
                    })
                }
    }
}