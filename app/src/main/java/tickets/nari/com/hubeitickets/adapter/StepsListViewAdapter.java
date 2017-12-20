package tickets.nari.com.hubeitickets.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tickets.nari.com.hubeitickets.R;


/**
 * Created by 谢仕宝 on 2017/10/18.
 * 操作项目列表
 */

public class StepsListViewAdapter extends BaseAdapter {

    private Context context;
    private List<Map<String, String>> data;
    private LayoutInflater layoutInflater;
    private boolean mIsYes = false;
    private boolean mIsMoren = false;
    private boolean mIsNo = false;
    private HashMap<Integer, View> map = new HashMap<Integer, View>();
    private int mCuttonP;

    public StepsListViewAdapter(Context context, List<Map<String, String>> data) {
        this.context = context;
        this.data = data;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {

        //  return data != null ? data.size() : 0;
        return 3;
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (map.get(i) == null) { //解决listview复用的问题
            holder = new ViewHolder();
            view = layoutInflater.inflate(R.layout.operation_steps_listview_item, null);
//            holder.tv_czbz_num = (TextView) view.findViewById(R.id.tv_czbz_num);
            holder.tv_czbz_time = (TextView) view.findViewById(R.id.tv_czbz_time);
            holder.tv_czbz_nr = (TextView) view.findViewById(R.id.tv_czbz_nr);
            holder.tv_lv_item_yes = (TextView) view.findViewById(R.id.tv_lv_item_yes);
            holder.tv_lv_item_moren = (TextView) view.findViewById(R.id.tv_lv_item_moren);
            holder.tv_lv_item_no = (TextView) view.findViewById(R.id.tv_lv_item_no);
            holder.ll_czbz_execute = (LinearLayout) view.findViewById(R.id.ll_czbz_execute);
            map.put(i, view);
            view.setTag(holder);
        } else {
            view = map.get(i);
            holder = (ViewHolder) view.getTag();
        }
        holder.tv_czbz_nr.setText(data.get(i).get("content"));
        holder.tv_czbz_time.setText(data.get(i).get("time"));
        if (mCuttonP == i) {  //用于刷新固定的item
            if (mIsYes) {
                holder.ll_czbz_execute.setBackgroundResource(R.mipmap.caozuo_yes);

                mIsYes = false;

            } else if (mIsMoren) {
                holder.ll_czbz_execute.setBackgroundResource(R.mipmap.caozuo_moren);

                mIsMoren = false;

            } else if (mIsNo) {
                holder.ll_czbz_execute.setBackgroundResource(R.mipmap.caozuo_no);

                mIsNo = false;
            }
        } else {

        }

        initListener(holder, i);
        return view;
    }


    private void initListener(ViewHolder holder, final int i) {
        holder.tv_lv_item_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIsYes = true;
                mCuttonP = i;
                notifyDataSetChanged();
                // notifyDataSetInvalidated();
            }
        });
        holder.tv_lv_item_moren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIsMoren = true;

                mCuttonP = i;
                notifyDataSetChanged();
                // notifyDataSetInvalidated();
            }
        });
        holder.tv_lv_item_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIsNo = true;

                mCuttonP = i;
                notifyDataSetChanged();
                // notifyDataSetInvalidated();
            }
        });
    }

    public static class ViewHolder {

        TextView tv_czbz_num;
        TextView tv_czbz_time;
        TextView tv_czbz_nr;
        TextView tv_lv_item_yes;
        TextView tv_lv_item_moren;
        TextView tv_lv_item_no;
        LinearLayout ll_czbz_execute;
    }
}
