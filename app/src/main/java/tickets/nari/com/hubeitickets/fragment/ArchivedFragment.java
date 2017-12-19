package tickets.nari.com.hubeitickets.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.nari.tickets.baselibrary.base.BasePullToRefresh;

import java.util.ArrayList;
import java.util.List;

import tickets.nari.com.hubeitickets.R;
import tickets.nari.com.hubeitickets.activity.OperationStepsActivity;
import tickets.nari.com.hubeitickets.adapter.CaoZuoPiao_List_Adapter;
import tickets.nari.com.hubeitickets.bean.ArchivedTicketListBean;
import tickets.nari.com.hubeitickets.customview.PullToRefreshLayout;


/**
 * Created by TQM on 2017/10/17.
 * 已归档界面
 */
public class ArchivedFragment extends android.support.v4.app.Fragment {

    private PullToRefreshLayout mPf_fragment_archived;
    private ListView mLv_fragment_archived;
    /**
     * 操作票列表信息集合
     */
    private List<ArchivedTicketListBean> ticketList = new ArrayList<ArchivedTicketListBean>();
    private Integer page = 1;
    private CaoZuoPiao_List_Adapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View v = inflater.inflate(R.layout.fragment_caozuopiao, container, false);
        mPf_fragment_archived = (PullToRefreshLayout) v.findViewById(R.id.pf_fragment_archived);
        mLv_fragment_archived = (ListView) v.findViewById(R.id.lv_fragment_archived);

        initData();
        return v;
    }

    /**
     * 处理界面数据
     */
    private void initData() {
        // TODO Auto-generated method stub
        mPf_fragment_archived.setOnStartListener(new BasePullToRefresh.OnStartListener() {
            @Override
            public void onStartRefresh(BasePullToRefresh var1) {
                //下拉刷新
                page = 1;
                getData(page + "");
            }

            @Override
            public void onStartLoadmore(BasePullToRefresh var1) {
                //上拉加载更多
                page++;
                getData(page + "");
            }
        });
        getData(page + "");
        mLv_fragment_archived.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(getActivity(), OperationStepsActivity.class);
                intent.putExtra("mbId", ticketList.get(position).getMBID());
                intent.putExtra("obj_id", ticketList.get(position).getOBJ_ID());
                startActivity(intent);
            }
        });
    }

    /*
        * 请求归档界面网络数据*/
    private void getData(String page) {
            if (this.page == 1) { //下拉刷新
                mPf_fragment_archived.refreshSuccess();
            } else {
                mPf_fragment_archived.loadmoreSuccess();
            }
                mPf_fragment_archived.setLoadmoreable(true);
            freshData();
    }

    private void freshData() {
        ArchivedTicketListBean archivedTicketListBean = new ArchivedTicketListBean();
        ticketList.add(archivedTicketListBean);
        ticketList.add(archivedTicketListBean);
        ticketList.add(archivedTicketListBean);
        if (mAdapter == null) {

            mAdapter = new CaoZuoPiao_List_Adapter(getActivity(), ticketList,"2");
            mLv_fragment_archived.setAdapter(mAdapter);

        } else {
            mAdapter.notifyDataSetChanged();
        }
    }


}
