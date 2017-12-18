package tickets.nari.com.hubeitickets.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

//import nari.app.caozuopiaopad.R;
//import nari.app.caozuopiaopad.activity.MainActivity;
//import nari.app.caozuopiaopad.activity.ProcessedActivity;
//import nari.app.caozuopiaopad.adapter.CheckAdapter;
//import nari.app.caozuopiaopad.base.BasePullToRefresh;
//import nari.app.caozuopiaopad.bean.TicketListBean;
//import nari.app.caozuopiaopad.customview.PullToRefreshLayout;
//import nari.app.caozuopiaopad.utils.DataReadUtil;

/**
 * Created by TQM on 2017/10/17.
 * 待处理界面
 */
public class ProcessedFragment extends android.support.v4.app.Fragment {
//    /**
//     * 上拉下拉刷新控件
//     */
//    private PullToRefreshLayout pf_fragment_check;
//    private ListView lv_fragment_check;
//    /**
//     * 操作票列表信息集合
//     */
//    private List<TicketListBean> ticketList = new ArrayList<TicketListBean>();
//    private StringBuilder sb;
//    private String mUserName;   //   mip用户名
//    private Integer page = 1;   //   页数
//    private String mObjId;   //   obj_Id
//    private String mMbId;  //   票模板ID
//    private CheckAdapter mAdapter;   //   待处理适配器
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        // TODO Auto-generated method stub
//        View v = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_check, null);
//        pf_fragment_check = (PullToRefreshLayout) v.findViewById(R.id.pf_fragment_check);
//        lv_fragment_check = (ListView) v.findViewById(R.id.lv_fragment_check);
//       // freshData();
//        initData();
//        return v;
//    }
//
//    /**
//     * 处理界面数据
//     */
//    private void initData() {
//        getData(page + "");
//        pf_fragment_check.setOnStartListener(new BasePullToRefresh.OnStartListener() {
//            @Override
//            public void onStartRefresh(BasePullToRefresh var1) {
//                //下拉刷新
//                page = 1;
//                getData(page + "");
//            }
//
//            @Override
//            public void onStartLoadmore(BasePullToRefresh var1) {
//                //上拉加载更多
//                page++;
//                getData(page + "");
//            }
//        });
//
//        lv_fragment_check.setOnItemClickListener(new OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view,
//                                    int position, long id) {  //   跳转到详情页
//                Intent intent = new Intent(getActivity(), ProcessedActivity.class);
//                intent.putExtra("mbId", ticketList.get(position).getMBID());
//                intent.putExtra("obj_id", ticketList.get(position).getOBJ_ID());
//                startActivity(intent);
//
//            }
//        });
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        // initData();
//    }
//
//    /*
//            * 请求待处理界面网络数据*/
//    private void getData(String page) {
//
//        mUserName = ((MainActivity) getActivity()).getUserName();
//        if (mUserName == null) {
//            Toast.makeText(getActivity(), "MIP登录失败，请重新登陆", Toast.LENGTH_SHORT).show();
//        } else {
//            sb = new StringBuilder();
//            //拼接请求体
//            sb.append("<list>").append("\n").append("<params>").append("\n")
//                    .append("<LOGIN_NAME>").append(mUserName)
//                    .append("</LOGIN_NAME>").append("\n").append("<PAGE_INDEX>")    //   页数
//                    .append(page).append("</PAGE_INDEX>").append("\n")
//                    .append("<PZT>").append("31").append("</PZT>")  //   票状态
//                    .append("\n").append("<PAGE_SIZE>").append("10").append("</PAGE_SIZE>")  //   每页数据
//                    .append("\n").append("<CZMD>").append("DCL")
//                    .append("</CZMD>").append("\n")
//                    .append("<GZDD>").append("10")
//                    .append("</GZDD>").append("\n").append("</params>")
//                    .append("\n").append("</list>");
//            String serviceName = "sxlp1"; //   服务名
//            String interfaceName = "getCZPListInfo";//   接口名
//            Object[] params = new Object[]{sb};
//            String data = DataReadUtil.getDataFromDb(serviceName, interfaceName, params);
//            if (this.page == 1) { //下拉刷新
//                ticketList.clear();//   清空数组
//                parseTicketInfo(data);
//                pf_fragment_check.refreshSuccess();
//
//            } else {
//                parseTicketInfo(data);
//                pf_fragment_check.loadmoreSuccess();
//
//            }
//
//
////            if (ticketList.size() < 10) {
////                pf_fragment_check.setLoadmoreable(false);
////            } else {
//                pf_fragment_check.setLoadmoreable(true);
////            }
//            freshData();
//        }
//
//
//    }
///*
//* 设置及刷新adapter
//* */
//    private void freshData() {
//
//        if (mAdapter == null) {
//            mAdapter = new CheckAdapter(getActivity(), ticketList);
//            lv_fragment_check.setAdapter(mAdapter);
//        } else {
//            mAdapter.notifyDataSetChanged();
//          // lv_fragment_check.setAdapter(mAdapter);
//            // mAdapter.setDatas(ticketList);
//        }
//    }
//
//    /**
//     * 解析操作票列表数据
//     *
//     * @author TQM
//     */
//    private void parseTicketInfo(String t) {
//        if (t == null && "".equals(t)) {
//            Toast.makeText(getActivity(), "数据为空", Toast.LENGTH_SHORT).show();
//        } else {
//            JSONObject data;
//            try {
//                data = new JSONObject(t);
//                JSONArray records = data.getJSONArray("records");
//                if (records.length() == 0) {
//                    Toast.makeText(getActivity(), "数据为空", Toast.LENGTH_SHORT).show();
//                } else {
//                    Log.e("lala", "records=======" + records.toString());
//                    for (int i = 0; i < records.length(); i++) {
//                        JSONObject record = records.getJSONObject(i);
//                        //工作票主键
//                        mObjId = record.getString("OBJ_ID");
//                        if ("null".equals(mObjId)) {
//                            mObjId = "";
//                        }
//                        //票模板id
//                        mMbId = record.getString("MBID");
//                        if ("null".equals(mMbId)) {
//                            mMbId = "";
//                        }
//                        String workPlace = record.getString("GZDDMC");    //工作地点
//                        if ("null".equals(workPlace)) {
//                            workPlace = "";
//                        }
//
//                        String club = record.getString("ZPBMMC");    //制票部门
//                        if ("null".equals(club)) {
//                            club = "";
//                        }
//                        String ticketNum = record.getString("PH");    //票号
//                        if ("null".equals(ticketNum)) {
//                            ticketNum = "";
//                        }
//                        String ticketContent = record.getString("CZRW");    //票内容
//                        if ("null".equals(ticketContent)) {
//                            ticketContent = "";
//                        }
//                        String receiveDate = record.getString("ZPSJ");    //制票时间
//                        if ("null".equals(receiveDate)) {
//                            receiveDate = "";
//                        }
//                        String pzt = record.getString("PZT");//票状态
//                        if ("null".equals(pzt)) {
//                            pzt = "";
//                        }
//                        String flrmc = record.getString("FLRMC");//   发令人名称
//                        if ("null".equals(flrmc)) {
//                            flrmc = "";
//                        }
//
//                        String slrmc = record.getString("SLRMC");//   受令人名称
//                        if ("null".equals(slrmc)) {
//                            slrmc = "";
//                        }
//                        TicketListBean ticketListBean = new TicketListBean();
//                        ticketListBean.setOBJ_ID(mObjId);
//                        ticketListBean.setMBID(mMbId);
//                        ticketListBean.setCZRW(ticketContent);
//                        ticketListBean.setPH(ticketNum);
//                        ticketListBean.setGZDDMC(workPlace);
//                        ticketListBean.setZPBMMC(club);
//                        ticketListBean.setZPSJ(receiveDate);
//                        ticketListBean.setPZT(pzt);
//                        ticketListBean.setFLRMC(flrmc);
//                        ticketListBean.setSLRMC(slrmc);
//                        ticketList.add(ticketListBean);
//                    }
//                }
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//
//            }
//        }
//
//
//    }
}
