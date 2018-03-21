package co.com.data.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity


/**
 * Created by oscarg798 on 3/20/18.
 */
@Entity(tableName = "company")
data class DBCompany(@ColumnInfo(name = "company_name") val name: String,
                     val catchPhrase: String,
                     val bs: String)