package co.com.core.entities

/**
 * Created by oscarg798 on 3/20/18.
 */
data class Post(val id: Int,
                val user: Int,
                val title: String,
                val body: String,
                val favorite: Boolean,
                val read: Boolean)