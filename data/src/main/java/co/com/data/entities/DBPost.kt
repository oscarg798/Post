package co.com.data.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by oscarg798 on 3/20/18.
 */
@Entity(tableName = "post")
data class DBPost(@PrimaryKey val id: Int,
                  val userId: Int,
                  val title: String,
                  val body: String,
                  val favorite: Boolean = false,
                  val read:Boolean = false)