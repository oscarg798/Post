package co.com.data.network

import co.com.data.entities.APIPost
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by oscarg798 on 3/20/18.
 */
interface IPostRoute {

    @GET("posts")
    fun getPosts(): Observable<List<APIPost>>

}