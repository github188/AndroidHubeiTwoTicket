package tickets.nari.com.hubeitickets.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.HashMap;

import tickets.nari.com.hubeitickets.fragment.ArchivedFragment;
import tickets.nari.com.hubeitickets.fragment.ExecutionFragment;
import tickets.nari.com.hubeitickets.fragment.ProcessedFragment;

/**
 * Created by lx on 2017/11/2.
 */

public class MainVPAdapter extends FragmentPagerAdapter {
    private final String[] titles = {"待处理", "执行中", "已归档"};
    private ArchivedFragment mProcessedFragment;
    private ArchivedFragment mExecutionFragment;
    private ArchivedFragment mArchivedFragment;
    private HashMap<Integer, Fragment> hashMap = new HashMap<>();

    public MainVPAdapter(FragmentManager fm, Context context) {
        super(fm);

    }

    @Override
    public Fragment getItem(int position) {

        if (hashMap.containsKey(position))
            return hashMap.get(position);
        else {
            switch (position) {
                case 0://   待处理
                    if (mProcessedFragment == null) {
                        mProcessedFragment = new ArchivedFragment();// ProcessedFragment();
                    }
                    hashMap.put(position, mProcessedFragment);
                    return mProcessedFragment;
                case 1://   执行中
                    if (mExecutionFragment == null) {
                        mExecutionFragment = new ArchivedFragment();//ExecutionFragment();
                    }
                    hashMap.put(position, mExecutionFragment);
                    return mExecutionFragment;
                case 2://   已归档
                    if (mArchivedFragment == null) {
                        mArchivedFragment = new ArchivedFragment();
                    }
                    hashMap.put(position, mArchivedFragment);
                    return mArchivedFragment;
                default:
                    return null;

            }
        }
    }

    @Override
    public int getCount() {
       return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        hashMap.remove(position);// 重写public void destroyItem(ViewGroup container, int position, Object object)，去掉super.destroyItem(container, position, object);
   //销毁fragment时，将该fragment从hashmap集合中移除；
    }
}
