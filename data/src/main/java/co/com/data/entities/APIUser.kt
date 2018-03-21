package co.com.data.entities


/**
 * Created by oscarg798 on 3/20/18.
 */
data class APIUser(val id: Int,
                   val name: String,
                   val username: String,
                   val email: String,
                   val phone: String,
                   val website: String,
                   val company: DBCompany,
                   val address: DBAddress)