package co.com.data.providers

import co.com.data.entities.APIUser
import co.com.data.entities.DBUser
import co.com.data.local.dao.UserDAO
import co.com.data.network.IUserRoute
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by oscarg798 on 3/20/18.
 */
class UserRepository : IUserRepository {


    @Inject
    lateinit var mUserRoute: IUserRoute

    @Inject
    lateinit var mUserDAO: UserDAO

    override fun getUsers(): Observable<List<APIUser>> {
        return mUserRoute.getUsers()
    }

    override fun getUsersFromDB(): List<DBUser> = mUserDAO.getAll()

    override fun getUser(id: Int): DBUser = mUserDAO.get(id)

    override fun insert(dbUser: DBUser){
        mUserDAO.insert(dbUser)
    }


}