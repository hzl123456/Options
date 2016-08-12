package cn.xmrk.options.activity;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.ArcMotion;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import cn.xmrk.options.R;
import cn.xmrk.options.pojo.ItemInfo;
import cn.xmrk.rkandroid.utils.CommonUtil;

/**
 * Created by Au61 on 2016/8/4.
 */
public class OptionsActivity extends AppCompatActivity {

    private static final String OPTION_IMAGE = "image";
    private static final String ITEM_ID = "itemId";
    private ItemInfo info;

    private ImageView ivHead;
    private TextView tvTitle;

    private ImageView ivToolhead;
    private ImageView ivToolHeadShow;
    private TextView tvText;

    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsing_toolbar;
    private AppBarLayout appbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        findViews();
        init();
        initToolBar();
        setUpWindowTransition();

    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setUpWindowTransition() {
        //进入的动画监听
        final ChangeBounds ts = new ChangeBounds();
        ts.setPathMotion(new ArcMotion());
        ts.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {


            }

            @Override
            public void onTransitionEnd(Transition transition) {
                ts.removeListener(this);
                animateRevealShow(ivToolhead);
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });
        getWindow().setSharedElementEnterTransition(ts);

    }

    /**
     * toolbar的展开动画
     **/
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void animateRevealShow(View viewRoot) {
        int cx = (viewRoot.getLeft() + viewRoot.getRight()) / 2;
        int cy = (viewRoot.getTop() + viewRoot.getBottom()) / 2;
        int finalRadius = Math.max(viewRoot.getWidth(), viewRoot.getHeight());
        Animator anim = ViewAnimationUtils.createCircularReveal(viewRoot, cx, cy, finalRadius / 3, finalRadius);
        viewRoot.setVisibility(View.VISIBLE);
        anim.setDuration(100);
        anim.start();
        //开始前设置标题的背景
        ivToolhead.setImageResource(R.color.bg_title_bar);

        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {

                //设置一个显示的动画
                ivToolHeadShow.animate().alpha(1).setStartDelay(100).start();
                tvText.animate().alpha(1).setStartDelay(100).start();
                tvTitle.animate().alpha(1).setStartDelay(100).start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }


    private void initToolBar() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(cn.xmrk.rkandroid.R.drawable.ic_white_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //重新设置toolbar的高度
        CollapsingToolbarLayout.LayoutParams layoutParams = (CollapsingToolbarLayout.LayoutParams) toolbar.getLayoutParams();
        layoutParams.height = CommonUtil.getStateBarHeight() + getResources().getDimensionPixelSize(R.dimen.title_bar_height);
        toolbar.setLayoutParams(layoutParams);
        //添加padding的距离
        toolbar.setPadding(0, CommonUtil.getStateBarHeight(), 0, 0);
        //设置颜色为白色
        collapsing_toolbar.setCollapsedTitleTextColor(getResources().getColor(R.color.bg_white));

        //添加滚动的监听
        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (-verticalOffset >= appBarLayout.getTotalScrollRange()) {
                    collapsing_toolbar.setTitle("请叫我百米冲刺");
                } else {
                    collapsing_toolbar.setTitle("");
                }
            }
        });
    }


    public static void StartOptionsActivity(AppCompatActivity activity, View transitionView, ItemInfo info) {
        Intent intent = new Intent(activity, OptionsActivity.class);
        intent.putExtra(ITEM_ID, info);
        // 这里指定了共享的视图元素
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, transitionView, OPTION_IMAGE);
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }

    private void findViews() {
        info = (ItemInfo) getIntent().getExtras().get(ITEM_ID);

        ivHead = (ImageView) findViewById(R.id.iv_head);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        ivToolhead = (ImageView) findViewById(R.id.iv_tool_head);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        collapsing_toolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        appbar = (AppBarLayout) findViewById(R.id.appbar);
        tvText = (TextView) findViewById(R.id.tv_text);
        ivToolHeadShow = (ImageView) findViewById(R.id.iv_tool_head_show);
    }

    private void init() {
        ivHead.setImageResource(info.imageRes);
        tvTitle.setText(info.title);
        // 这里指定了被共享的视图元素
        ViewCompat.setTransitionName(ivHead, OPTION_IMAGE);
    }

}
