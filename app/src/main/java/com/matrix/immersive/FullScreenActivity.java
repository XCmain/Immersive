package com.matrix.immersive;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.matrix.immersive.R;
import com.matrix.immersive.utils.ImmersiveUtil;

/**
 * <p> 描述：全屏activity沉浸效果</p>
 * <p> 作者：xc</p>
 * <p> 时间：2020/04/09 11:23</p>
 */
public class FullScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);
        ImmersiveUtil.setFullScreen(this);
    }
}
