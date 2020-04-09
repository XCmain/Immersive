package com.matrix.immersive;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.matrix.immersive.fragment.CaseFragment;
import com.matrix.immersive.fragment.ClassificationFragment;
import com.matrix.immersive.fragment.HomeFragment;
import com.matrix.immersive.fragment.SettingFragment;
import com.matrix.immersive.utils.ImmersiveUtil;

public class FragmentActivity extends AppCompatActivity {

    private static final String TAG = "FragmentActivity";
    private Fragment[] mFragments = new Fragment[]{new HomeFragment(), new ClassificationFragment(),
            new CaseFragment(), new SettingFragment()};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        ImmersiveUtil.setTranslucent(this, false);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // true to display the item as the selected item and false if the item should not be
                // selected. Consider setting non-selectable items as disabled preemptively to make them
                // appear non-interactive.
                // 点击菜单切换
                navigationItemSelected(item.getItemId());
                return true;
            }
        });

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_content, mFragments[0]).commit();
    }

    private void navigationItemSelected(int itemId) {
        Fragment fragment = null;
        if (itemId == R.id.tab_home) {
            fragment = mFragments[0];
        } else if (itemId == R.id.tab_classification) {
            fragment = mFragments[1];
        } else if (itemId == R.id.tab_case) {
            fragment = mFragments[2];
        } else {
            fragment = mFragments[3];
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_content, fragment).commit();
    }
}
