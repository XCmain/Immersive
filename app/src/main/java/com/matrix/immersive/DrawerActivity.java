package com.matrix.immersive;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.internal.NavigationMenuView;
import com.google.android.material.navigation.NavigationView;
import com.matrix.immersive.utils.ImmersiveUtil;

/**
 * <p> 描述：抽屉状态栏全透明</p>
 * <p> 作者：xc</p>
 * <p> 时间：2020/04/09 17:49</p>
 */
public class DrawerActivity extends AppCompatActivity {

    private View mRootView;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        // 设置状态栏全透明
        ImmersiveUtil.setTranslucent(this, false);

        mRootView = findViewById(R.id.main_layout);

        findViewById(R.id.btn_open_drawer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(Gravity.LEFT);
            }
        });

        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                return false;
            }
        });

        // 设置过度滚动模式，滚动到最底部时再往下滚，会出现圆弧阴影
        navigationView.setOverScrollMode(View.OVER_SCROLL_ALWAYS);

        // 隐藏抽屉的滚动条
        View childAt = navigationView.getChildAt(0);
        if (childAt instanceof NavigationMenuView) {
            childAt.setVerticalScrollBarEnabled(false);
        }

        mDrawerLayout = findViewById(R.id.drawer_layout);
        // 设置抽屉显示时状态栏全透明
        mDrawerLayout.setFitsSystemWindows(true);
        mDrawerLayout.setClipToPadding(false);
        // 监听抽屉的行为
        mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                // 随着抽屉的滑出，主页面相应隐藏
                int width = drawerView.getWidth();
                mRootView.setTranslationX(width * slideOffset);
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
                // 抽屉完全打开
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                // 抽屉完全关闭
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                // 抽屉的状态改变 STATE_IDLE, STATE_DRAGGING, STATE_SETTLING
            }
        });

        // 设置抽屉view的宽度
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int heightPixels = displayMetrics.heightPixels;
        int widthPixels = displayMetrics.widthPixels;
        ViewGroup.LayoutParams para = navigationView.getLayoutParams();
        para.width = (int) (widthPixels * 0.8);
        para.height = heightPixels;
        navigationView.setLayoutParams(para);

    }
}
