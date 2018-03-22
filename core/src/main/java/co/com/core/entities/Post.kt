package co.com.core.entities

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by oscarg798 on 3/20/18.
 */
data class Post(val id: Int,
                val user: Int,
                val title: String,
                val body: String,
                val favorite: Boolean,
                val read: Boolean) : Parcelable {

    constructor(source: Parcel) : this(
            source.readInt(),
            source.readInt(),
            source.readString(),
            source.readString(),
            1 == source.readInt(),
            1 == source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(id)
        writeInt(user)
        writeString(title)
        writeString(body)
        writeInt((if (favorite) 1 else 0))
        writeInt((if (read) 1 else 0))
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Post> = object : Parcelable.Creator<Post> {
            override fun createFromParcel(source: Parcel): Post = Post(source)
            override fun newArray(size: Int): Array<Post?> = arrayOfNulls(size)
        }
    }
}