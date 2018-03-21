package co.com.data.providers

import co.com.data.entities.APIUser
import co.com.data.entities.DBUser
import io.reactivex.Observable

/**
 * Created by oscarg798 on 3/20/18.
 */
interface IUserRepository {
    fun getUsers(): Observable<List<APIUser>>

    fun getUsersFromDB(): List<DBUser>

    fun getUser(id: Int): DBUser

    fun insert(dbUser: DBUser)
}