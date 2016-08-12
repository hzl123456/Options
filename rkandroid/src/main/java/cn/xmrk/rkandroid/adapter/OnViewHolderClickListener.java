package cn.xmrk.rkandroid.adapter;

import android.support.v7.widget.RecyclerView;

/**
 * ViewHolder的根View被点击
 */
public interface OnViewHolderClickListener<D extends RecyclerView.ViewHolder> {

    void OnViewHolderClick(D holder);

    void OnViewHolderLongClick(D holder);

    void OnViewHolderRemove(D holder);

}
