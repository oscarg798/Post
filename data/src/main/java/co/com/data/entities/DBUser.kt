package co.com.data.entities

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by oscarg798 on 3/20/18.
 */
@Entity(tableName = "user")
data class DBUser(@PrimaryKey val id: Int,
                  val name: String,
                  val username: String,
                  val email: String,
                  val phone: String,
                  val website: String,
                  @Embedded val company: DBCompany,
                  @Embedded val address: DBAddress)