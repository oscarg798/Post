package co.com.data.network

import co.com.data.entities.APIUser
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by oscarg798 on 3/20/18.
 */
interface IUserRoute{

    @GET("users")
    fun getUsers():Observable<List<APIUser>>
}