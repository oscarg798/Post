package co.com.post.post_list

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.widget.CompoundButton
import co.com.post.IBasePresenter

/**
 * Created by oscarg798 on 3/21/18.
 */
interface IPostListFragmentPresenter : IBasePresenter<IPostListFragmentView>,
        CompoundButton.OnCheckedChangeListener,
        PostListCallbacks {


    fun onSwipe(viewHolder: RecyclerView.ViewHolder?, direction: Int)

    fun onMenuItemSelected(itemId: Int)


}