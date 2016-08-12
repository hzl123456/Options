package cn.xmrk.options.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import cn.xmrk.options.R;
import cn.xmrk.options.adapter.ItemAdapter;
import cn.xmrk.options.pojo.ItemInfo;
import cn.xmrk.rkandroid.activity.BaseActivity;
import cn.xmrk.rkandroid.adapter.OnViewHolderClickListener;
import cn.xmrk.rkandroid.utils.CommonUtil;
import cn.xmrk.rkandroid.utils.uil.SpacesItemDecoration;

public class MainActivity extends BaseActivity {

    private RecyclerView rvContent;
    private ItemAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setTopShowView(View.inflate(this, R.layout.activity_main_show, null));
    }

    private void findViews() {
        rvContent = (RecyclerView) findViewById(R.id.rv_content);
        mAdapter = new ItemAdapter(initDatas());
        rvContent.setAdapter(mAdapter);
        rvContent.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvContent.addItemDecoration(new SpacesItemDecoration(0, CommonUtil.dip2px(7), 0, 0));

        mAdapter.setOnViewHolderClickListener(new OnViewHolderClickListener<ItemAdapter.ItemViewHolder>() {
            @Override
            public void OnViewHolderClick(ItemAdapter.ItemViewHolder holder) {
                OptionsActivity.StartOptionsActivity(MainActivity.this, holder.ivHead, holder.info);
            }

            @Override
            public void OnViewHolderLongClick(ItemAdapter.ItemViewHolder holder) {

            }

            @Override
            public void OnViewHolderRemove(ItemAdapter.ItemViewHolder holder) {

            }
        });
    }


    private List<ItemInfo> initDatas() {
        List<ItemInfo> infos = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            infos.add(new ItemInfo(R.drawable.ic_launcher, "POSITION" + (i + 1)));
        }
        return infos;
    }


}
