package com.practice.jackchung.lexiangmusicplayer.activity;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.practice.jackchung.lexiangmusicplayer.R;
import com.practice.jackchung.lexiangmusicplayer.fragment.LocalMusicListFragment;
import com.practice.jackchung.lexiangmusicplayer.fragment.NetMusicListFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, RadioGroup.OnCheckedChangeListener {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private RadioGroup radioGroup;
    private ImageButton imageButton_myActionBar_leftButton;
    private ImageButton imageButton_myActionBar_rightButton;
    private RadioButton radioButton_myActionBar_music;
    private RadioButton radioButton_myActionBar_friends;
    private RadioButton radioButton_myActionBar_discover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialUI();
    }

    private void initialUI() {
        initialDrawerLayout();
        initialActionBar();
    }

    private void initialActionBar() {
        // 1. 设置ToolBar为页面上的actionBar
        LinearLayout linearLayout_MyActionBar = (LinearLayout)findViewById(R.id.myActionBar_MainActivity_ActionBar);

        // 2. 初始化ActionBar上的RadioGroup
        // 2.1 解析一个LinearLayout布局
        LinearLayout inflate_myactionbar_layout = (LinearLayout) View.inflate(this,R.layout.inflate_myactionbar_layout,linearLayout_MyActionBar);

        // 2.2 初始化一个RadioGroup
        radioGroup = (RadioGroup)inflate_myactionbar_layout.findViewById(R.id.radioGroup_MyActionBar_RadioGroup);
        // 2.3 初始化两个ImageButton
        imageButton_myActionBar_leftButton = (ImageButton)inflate_myactionbar_layout.findViewById(R.id.imageButton_MyActionBar_LeftButton);
        imageButton_myActionBar_rightButton = (ImageButton)inflate_myactionbar_layout.findViewById(R.id.imageButton_MyActionBar_RightButton);

        // 2.4 初始化三个RadioButton
        radioButton_myActionBar_music = (RadioButton)radioGroup.findViewById(R.id.radioButton_MyActionBar_Music);
        radioButton_myActionBar_friends = (RadioButton)radioGroup.findViewById(R.id.radioButton_MyActionBar_Friends);
        radioButton_myActionBar_discover = (RadioButton)radioGroup.findViewById(R.id.radioButton_MyActionBar_Discover);

        // 2.5 给RadioGroup设置监听器
        radioGroup.setOnCheckedChangeListener(this);

        // 2.6 设置RadioGroup默认的选中
        radioButton_myActionBar_music.setChecked(true);
        imageButton_myActionBar_leftButton.setImageResource(R.drawable.align_left);
    }

    private void initialDrawerLayout() {
        // 1. 初始化DrawerLayout
        drawerLayout = (DrawerLayout) findViewById(R.id.dl_DrawerLayout);
        navigationView = (NavigationView) findViewById(R.id.nv_NavigationView);
        navigationView.setNavigationItemSelectedListener(this);

        // 2. 获得HeaderView
        View headerView = navigationView.getHeaderView(0);
        Log.i("TAG","Header.headerView="+headerView);

        // 设置HeaderView中的headerText
        TextView tv_HeadTextInNavigationView = (TextView) headerView.findViewById(R.id.textView_HeaderText);//navigationView.getHeaderView(0);
        Log.i("TAG","Header.TextView="+tv_HeadTextInNavigationView);
        tv_HeadTextInNavigationView.setText("游客");
        //tv_HeadTextInNavigationView.setText(MyApplication.user!=null?MyApplication.user:"游客");

        // 2.新建actionBarDrawerToggle对象
        actionBarDrawerToggle = new ActionBarDrawerToggle(
                this, drawerLayout, R.string.drawer_open,R.string.drawer_close);
        // 3. 添加监听
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        // 4. 同步DrawerLayout状态
        actionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.navigation_item_1:
                Log.i("TAG","onNavigationItemSelected.nav_item_1");
                //跳转到最近播放页面
//                startActivity(new Intent(this,HistoryPlayActivity.class));
//                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.navigation_item_2:
                Log.i("TAG","onNavigationItemSelected.nav_sub_item_2");
                // 1. 执行退出操作
                // 2. 关闭侧滑菜单
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            default:
                break;
        }
        return true;
    }


    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        Log.i("TAG","onCheckedChanged");
        // 1.获得FragmentManager对象
        FragmentManager fragmentManager = getSupportFragmentManager();
        // 2.开启事务
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (checkedId){
            case R.id.radioButton_MyActionBar_Music:
                Toast.makeText(this,"本地音乐",Toast.LENGTH_SHORT).show();
                Log.i("TAG","本地音乐");
                // 3.替换Fragment为本地音乐Fragment(LocalMusicFragment)
                fragmentTransaction.replace(R.id.FrameLayout_MainContent_FrameLayout,new LocalMusicListFragment(),"fragment_LocalMusicList");
                break;
            case R.id.radioButton_MyActionBar_Friends:
                Toast.makeText(this,"朋友圈",Toast.LENGTH_SHORT).show();
                Log.i("TAG","朋友圈");
                break;
            case R.id.radioButton_MyActionBar_Discover:
                Toast.makeText(this,"发现",Toast.LENGTH_SHORT).show();
                Log.i("TAG","发现");
                // 3.替换Fragment为网络音乐Fragment(LocalMusicFragment)
                fragmentTransaction.replace(R.id.FrameLayout_MainContent_FrameLayout,new NetMusicListFragment(),"fragment_NetMusicList");
                break;
            default:

                break;
        }
        // 4.提交事务
        fragmentTransaction.commit();
    }
}
