package com.matrix.immersive.utils;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

/**
 * <p> 描述：沉浸式效果工具</p>
 * <p> 作者：xc</p>
 * <p> 时间：2020/04/02</p>
 */
public class ImmersiveUtil {
    private static final String TAG = "ImmersiveUtil";

    /**
     * 设置全屏，隐藏状态栏和导航栏，下拉时状态栏以灰色背景显示，一段时间后自动再次隐藏
     */
    public static void setFullScreen(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 4.4及以上
            View decorView = activity.getWindow().getDecorView();
            int options = View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            decorView.setSystemUiVisibility(options);
        } else {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }

    /**
     * 设置状态栏全透明，状态栏覆盖在实际内容之上显示
     *
     * @param activity
     */
    public static void setTranslucent(Activity activity, boolean isTextBlack) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && isTextBlack) {
            // 6.0及以上有效，可以将状态栏字体改为黑色
            View decorView = activity.getWindow().getDecorView();
            int options = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            decorView.setSystemUiVisibility(options);
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decorView = activity.getWindow().getDecorView();
            int options = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(options);
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 设置了该flag时，setStatusBarColor方法会无效
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    /**
     * 设置状态栏字体黑色
     */
    public static void setStatusTextBlack(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // 6.0及以上有效
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            View decorView = activity.getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            activity.getWindow().setStatusBarColor(Color.RED);
        }
    }

    /**
     * 设置状态栏颜色
     *
     * @param activity
     */
    private static void setStatusColor(Activity activity, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().setStatusBarColor(color);
        }
    }

    public static int getStatusHeight(Activity activity) {
        int identifier = activity.getResources().getIdentifier("status_bar_height",
                "dimen", "android");
        int size = activity.getResources().getDimensionPixelSize(identifier);
        Log.d(TAG, "height:" + size + "px");// 25dp 状态栏高度
        return size;
    }
}
