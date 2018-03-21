package co.com.data.local.dao

import android.arch.persistence.room.*
import co.com.data.entities.DBUser
import io.reactivex.Flowable

/**
 * Created by oscarg798 on 3/20/18.
 */
@Dao
interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(dbUser: DBUser)

    @Query("Select * from user")
    fun getAll(): List<DBUser>

    @Query("Select * from user where id=:id")
    fun get(id: Int): DBUser

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun update(dbUser: DBUser)

    @Delete
    fun delete(dbUser: DBUser)
}