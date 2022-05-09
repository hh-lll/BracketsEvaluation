package com.android.example.cameraxbasic;

import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.view.Window;
import android.widget.RadioGroup;

import com.android.example.cameraxbasic.ui.historical.HistorialFragment;
import com.android.example.cameraxbasic.ui.knowledge.KnowledgeFragment;
import com.android.example.cameraxbasic.ui.photo.PhotoFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.android.example.cameraxbasic.databinding.ActivityMainBinding;
import com.android.example.cameraxbasic.ui.*;

public class MainActivity extends AppCompatActivity {

private ActivityMainBinding binding;

    private RadioGroup mTabRadioGroup;
    private SparseArray<Fragment> mFragmentSparseArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //隐藏导航栏
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
     binding = ActivityMainBinding.inflate(getLayoutInflater());
     setContentView(binding.getRoot());
     initView();
//        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
////        NavigationUI.setupWithNavController(binding.navView, navController);
    }
    private void initView() {
        mTabRadioGroup = findViewById(R.id.tabs_rg);
        mFragmentSparseArray = new SparseArray<>();
        mFragmentSparseArray.append(R.id.his_tab, new HistorialFragment());
        mFragmentSparseArray.append(R.id.photo_tab, new PhotoFragment());
        mFragmentSparseArray.append(R.id.know_tab, new KnowledgeFragment());
        View root = binding.getRoot();
        root.getBackground().setAlpha(50);//0~255透明度值 0：全透明；255不透明
        mTabRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // 具体的fragment切换逻辑可以根据应用调整，例如使用show()/hide()
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        mFragmentSparseArray.get(checkedId)).commit();
            }
        });
        // 默认显示第一个
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,
                mFragmentSparseArray.get(R.id.photo_tab)).commit();

    }

}