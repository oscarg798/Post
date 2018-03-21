package co.com.post.post_list

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import co.com.post.R
import kotlinx.android.synthetic.main.post_item.view.*

/**
 * Created by oscarg798 on 3/21/18.
 */
class PostItemViewHolder(mItemView: View) : RecyclerView.ViewHolder(mItemView) {


    val mTVPostTitle = mItemView.findViewById<TextView>(R.id.mTVPostTitle)
    val mIVFavorite = mItemView.findViewById<ImageView>(R.id.mIVFavorite)
    val mIVRead = mItemView.findViewById<ImageView>(R.id.mIVRead)
}