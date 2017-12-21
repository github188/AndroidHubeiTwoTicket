package tickets.nari.com.hubeitickets.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import tickets.nari.com.hubeitickets.R;


/**
 * 详情中操作项目列表（最多只展示三个）
 * Created by xieshibao on 2017/12/20.
 */

public class CaoZuoXM_ListAdapter extends BaseAdapter {
    private Context context;

    public CaoZuoXM_ListAdapter(Context context, List<Object> czxm_List) {//List<Object> czxm_List为后台取回的操作项目的值的集合，用于展示，
        this.context = context;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_czxm_adapter, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        return view;
    }

    static class ViewHolder {
        public TextView tv_exe_order_one;
        public TextView tv_exe_czxm_one;
        public TextView tv_exe_zxsj_one;

        private ViewHolder(View view) {
            tv_exe_order_one = view.findViewById(R.id.tv_exe_order_one);
            tv_exe_czxm_one = view.findViewById(R.id.tv_exe_czxm_one);
            tv_exe_zxsj_one = view.findViewById(R.id.tv_exe_zxsj_one);

        }
    }

}
