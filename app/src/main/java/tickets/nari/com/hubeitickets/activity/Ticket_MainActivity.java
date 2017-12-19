package tickets.nari.com.hubeitickets.activity;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import tickets.nari.com.hubeitickets.R;
import tickets.nari.com.hubeitickets.adapter.MainVPAdapter;

public class Ticket_MainActivity extends FragmentActivity {
    //滑动页面
    private DrawerLayout drawerLayout;
    //滑动菜单
//    private NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setContentView(R.layout.activity_ticket__main);
        initView();
    }


    /**
     * 初始化页面控件
     */
    private void initView() {
        TabLayout tab_bar = (TabLayout) findViewById(R.id.tab_bar);
        ViewPager vp_main_activity = (ViewPager) findViewById(R.id.vp_main_activity);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        MainVPAdapter adapter = new MainVPAdapter(getSupportFragmentManager(), getApplicationContext());
        vp_main_activity.setAdapter(adapter);
        tab_bar.setupWithViewPager(vp_main_activity);

    }


    /**
     * 点击返回键处理
     */
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();

        }
    }

}
