package cn.xmrk.options.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.xmrk.options.R;
import cn.xmrk.options.pojo.ItemInfo;
import cn.xmrk.rkandroid.adapter.BaseRecycleAdapter;
import cn.xmrk.rkandroid.adapter.viewholder.BaseRecycleViewHolder;

/**
 * Created by Au61 on 2016/8/4.
 */
public class ItemAdapter extends BaseRecycleAdapter<ItemInfo, ItemAdapter.ItemViewHolder> {


    public ItemAdapter(List<ItemInfo> mData) {
        super(mData);
    }


    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(View.inflate(parent.getContext(), R.layout.layout_item, null));
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.tvTitle.setText(holder.info.title);
        holder.ivHead.setImageResource(holder.info.imageRes);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnViewHolderClickListener != null) {
                    mOnViewHolderClickListener.OnViewHolderClick(holder);
                }
            }
        });
    }

    public class ItemViewHolder extends BaseRecycleViewHolder<ItemInfo> {

        public ImageView ivHead;
        public TextView tvTitle;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ivHead = (ImageView) itemView.findViewById(R.id.iv_head);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }
}
