package co.com.data.local.dao

import android.arch.persistence.room.*
import co.com.data.entities.DBPost
import io.reactivex.Flowable

/**
 * Created by oscarg798 on 3/20/18.
 */
@Dao
interface PostDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(dbPost: DBPost)

    @Query("Select * from post")
    fun getAll(): List<DBPost>

    @Query("Select * from post where id=:id")
    fun get(id: Int): DBPost?

    @Query("Select * from post where favorite=1")
    fun getFavorites(): List<DBPost>

    @Update(onConflict = OnConflictStrategy.ABORT)
    fun update(dbPost: DBPost)

    @Delete
    fun delete(dbPost: DBPost)

    @Query("Delete from post")
    fun deleteAll()


}