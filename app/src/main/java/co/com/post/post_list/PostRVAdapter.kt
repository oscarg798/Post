package co.com.post.post_list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.com.core.entities.Post
import co.com.post.R

/**
 * Created by oscarg798 on 3/21/18.
 */
class PostRVAdapter(private val mPostList: ArrayList<Post> = ArrayList(),
                    val mCallbacks: PostListCallbacks) :
        RecyclerView.Adapter<PostItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostItemViewHolder {
        return PostItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.post_item,
                parent, false))
    }

    override fun getItemCount(): Int {
        return mPostList.size
    }

    override fun onBindViewHolder(holder: PostItemViewHolder, position: Int) {
        holder.mTVPostTitle.text = mPostList[position].title

        holder.itemView.setOnClickListener {
            mCallbacks.postSelected(mPostList[holder.adapterPosition])
            holder.mIVRead.visibility = View.GONE
        }

        holder.mIVRead.visibility = if (mPostList[position].read) View.GONE else View.VISIBLE

        holder.mIVFavorite.setImageDrawable(if (mPostList[position].favorite)
            holder.mIVFavorite.resources.getDrawable(R.drawable.ic_favorite) else
            holder.mIVFavorite.resources.getDrawable(R.drawable.ic_favorite_border))


        holder.mIVFavorite.setOnClickListener {
            mCallbacks.favoriteButtonClick(mPostList[holder.adapterPosition])
        }

    }

    fun clear() {
        mPostList.clear()
        notifyDataSetChanged()
    }

    fun add(post: List<Post>) {
        mPostList.addAll(post)
        notifyDataSetChanged()
    }

    fun update(post: Post) {
        val postToUpdate = mPostList.filter {
            it.id == post.id
        }
        if (postToUpdate.isNotEmpty()) {
            val postPosition = mPostList.indexOf(postToUpdate[0])
            mPostList.removeAt(postPosition)
            mPostList.add(postPosition, post)
            notifyItemChanged(postPosition)
        }

    }

    fun delete(position: Int) {
        if (mPostList.size > position) {
            mPostList.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun getPostOnAdapter(): ArrayList<Post> {
        return mPostList
    }

    fun getPost(position: Int): Post? {
        if (mPostList.size > position) {
            return mPostList[position]
        }

        return null
    }
}