package co.com.data.entities

import android.arch.persistence.room.Entity

/**
 * Created by oscarg798 on 3/20/18.
 */
@Entity(tableName = "geo")
data class DBGeo(val lat: Double,
                 val lng: Double)