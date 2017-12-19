package tickets.nari.com.hubeitickets.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import tickets.nari.com.hubeitickets.R;
import tickets.nari.com.hubeitickets.bean.ArchivedTicketListBean;

/**
 * Created by xieshibao on 2017/12/18.
 * 操作票三种状态的列表界面
 */

public class CaoZuoPiao_List_Adapter extends BaseAdapter {
    private Context mContext;
    private List<ArchivedTicketListBean> ticketList; //   票列表集合
    private String status = "0";
    public CaoZuoPiao_List_Adapter(Context mContext, List<ArchivedTicketListBean> ticketList,String status) {
        this.mContext = mContext;
        this.ticketList = ticketList;
        this.status = status;
    }
    @Override
    public int getCount() {
        if (ticketList == null) {
            return 0;
        }else {
            return ticketList.size();
        }
    }

    @Override
    public Object getItem(int position) {
        if (ticketList != null) {
            return ticketList.get(position);
        }
        return position;
    }
    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.item_fragment_caozuopiao_adapter, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

//        String status = ticketList.get(position).getPZT();
        if("0".equals(status)) { //   待审核
            holder.iv_ygd_status.setText("待处理");

            holder.iv_ygd_status.setBackgroundResource(R.drawable.textview_border_red);
        }else if ("1".equals(status)) { //   执行中
            holder.iv_ygd_status.setText("执行中");
            holder.iv_ygd_status.setTextColor(Color.parseColor("#fdb96d"));
            holder.iv_ygd_status.setBackgroundResource(R.drawable.textview_border_orange);
        }else if ("2".equals(status)) { //   已归档
            holder.iv_ygd_status.setText("已归档");
            holder.iv_ygd_status.setTextColor(Color.parseColor("#f0f0f0"));
            holder.iv_ygd_status.setBackgroundResource(R.drawable.textview_border_dark);
        }
//        holder.tv_piaohao.setText("");
//        holder.tv_content.setText("");
//        holder.tv_caozuoren.setText("");
//
//        String time = ticketList.get(position).getZPSJ();
//        if (time.length() >= 18) {
//           String timeSub = time.substring(0, time.length() - 5);
//            holder.tv_date.setText(timeSub);
//        } else {
//            holder.tv_date.setText(time);
//        }

        return convertView;
    }

    public static class ViewHolder {
        TextView iv_ygd_status;
        TextView tv_piaohao;
        TextView tv_content;
        TextView tv_caozuoren;
        TextView tv_date;

        private ViewHolder(View convertView){
             iv_ygd_status = (TextView) convertView.findViewById(R.id.iv_ygd_status);
            tv_piaohao = (TextView) convertView.findViewById(R.id.tv_piaohao);
            tv_content = (TextView) convertView.findViewById(R.id.tv_content);
            tv_caozuoren = (TextView) convertView.findViewById(R.id.tv_caozuoren);
            tv_date = (TextView) convertView.findViewById(R.id.tv_date);
        }
    }
}
