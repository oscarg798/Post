package co.com.data.entities

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity

/**
 * Created by oscarg798 on 3/20/18.
 */
@Entity(tableName = "address")
data class DBAddress(val street: String,
                     val suite: String,
                     val city: String,
                     val zipCode: String?,
                     @Embedded val geo:DBGeo)
