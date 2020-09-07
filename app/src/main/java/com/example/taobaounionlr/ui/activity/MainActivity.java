package com.example.taobaounionlr.ui.activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.taobaounionlr.R;
import com.example.taobaounionlr.base.BaseFragment;
import com.example.taobaounionlr.ui.fragment.HomeFragment;
import com.example.taobaounionlr.ui.fragment.RedPacketFragment;
import com.example.taobaounionlr.ui.fragment.SearchFragment;
import com.example.taobaounionlr.ui.fragment.SelectedFragment;
import com.example.taobaounionlr.utils.LogUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private  static final String TAG = "MainActivity";
    @BindView(R.id.main_navigation_bar)
    public BottomNavigationView mNavigationView;
    private HomeFragment mHomeFragment;
    private RedPacketFragment mRedPacketFragment;
    private SearchFragment mSearchFragment;
    private SelectedFragment mSelectedFragment;
    private FragmentManager mFm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        initView();
        initListener();
        initFragment();
    }

    private void initFragment() {
        mHomeFragment = new HomeFragment();
        mRedPacketFragment = new RedPacketFragment();
        mSearchFragment = new SearchFragment();
        mSelectedFragment = new SelectedFragment();
        mFm = getSupportFragmentManager();
        //默认选中第一页
        switchFragment(mHomeFragment);
    }

    private void initListener() {
        mNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                 //Log.d("标题---->", (String) item.getTitle());
                 if (item.getItemId() == R.id.home){
                     //Log.d(TAG,"切换到首页");
                     LogUtils.d(this,"切换到首页");
                     switchFragment(mHomeFragment);
                 }else if (item.getItemId() == R.id.selected){
                     //Log.d(TAG,"切换到精选");
                     LogUtils.i(this,"切换到精选");
                     switchFragment(mSelectedFragment);
                 }else if (item.getItemId() == R.id.red_packet){
                     //Log.d(TAG,"切换为特惠");
                     LogUtils.w(this,"切换为特惠");
                     switchFragment(mRedPacketFragment);
                 }else if(item.getItemId() == R.id.search){
                     //Log.d(TAG,"切换到搜索");
                     LogUtils.e(this,"切换到搜索");
                     switchFragment(mSearchFragment);
                 }
                return true;
            }
        });
    }

    private void switchFragment(BaseFragment targetFragmnet) {
        FragmentTransaction fragmentTransaction = mFm.beginTransaction();
        fragmentTransaction.replace(R.id.main_page_container,targetFragmnet);
        fragmentTransaction.commit();//提交事物
    }

//    private void initView() {
//        //mNavigationView = findViewById(R.id.main_navigation_bar);
//
//       FragmentManager fm =  getSupportFragmentManager();
//       FragmentTransaction transaction = fm.beginTransaction();
//       transaction.add(R.id.main_page_container,homeFragment);
//       transaction.commit();
//    }
}