package tickets.nari.com.hubeitickets.customview;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import tickets.nari.com.hubeitickets.R;


/**
 * 选择弹框
 * Created by xieshibao on 2017/10/23.
 */

public class CustomSignatureDialog {
    private Context context;
    private Dialog mDialog;
    private String mName;
    private String mClub;
    private TextView tv_qm_title;
    private String objId;
    private String mbId;
    MyListener myListener;
    private String flag;

    public CustomSignatureDialog(Context context, String objId, String mbId, MyListener myListener) {
        this.context = context;
        this.objId = objId;
        this.mbId = mbId;
        this.myListener = myListener;
        initView();
    }

    private void initView() {
        this.mDialog = new Dialog(context, R.style.dialogSignaStyle);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_signature, null);
        tv_qm_title = view.findViewById(R.id.tv_qm_title);
        final Spinner spinner_club = (Spinner) view.findViewById(R.id.spinner_club);
        final Spinner spinner_name = (Spinner) view.findViewById(R.id.spinner_name);
        final EditText et_pw = (EditText) view.findViewById(R.id.et_pw);
        Button bt_qm_sure = (Button) view.findViewById(R.id.bt_qm_sure);
        Button bt_cancle_qm = (Button) view.findViewById(R.id.bt_cancle_qm);// 取消签名
        ImageView iv_qm_close = (ImageView) view.findViewById(R.id.iv_qm_close);
        getSignatureData();
        //获取用户名称和用户部门
//        SharedPreferencesHelper sharedPreferencesHelper = new SharedPreferencesHelper(context, "PersonalInformation");
//        String userName = sharedPreferencesHelper.getStringValue("userName");
//        String DEPTNAME = sharedPreferencesHelper.getStringValue("DEPTNAME");

        String userName = "userName";
        String DEPTNAME = "DEPTNAME";
        
        // 建立数据源
        if (userName == null && DEPTNAME == null) {
            userName = "";
            DEPTNAME = "";
        }
        final String[] mItems1 = {DEPTNAME,"2"};//测试数据
        final String[] mItems2 = {userName,"2"};
        // 建立Adapter并且绑定数据源
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(context,
                android.R.layout.simple_spinner_item, mItems1);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(context,
                android.R.layout.simple_spinner_item, mItems2);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_club.setAdapter(adapter1);
        spinner_name.setAdapter(adapter2);
        spinner_club
                .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView,
                                               View view, int i, long l) {
                        mClub = mItems1[i];
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
        spinner_name
                .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView,
                                               View view, int i, long l) {
                        Log.e("lala", "选中的是========" + mItems2[i]);
                        mName = mItems2[i];
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
        iv_qm_close.setOnClickListener(new OnClickListener() { // 关闭dialog
            @Override
            public void onClick(View view) {

                mDialog.cancel();
            }
        });
        //确认按钮
        bt_qm_sure.setOnClickListener(new OnClickListener() { // 确认按钮
            @Override
            public void onClick(View view) {
                String password = et_pw.getText().toString().trim();
                if (TextUtils.isEmpty(mClub) || TextUtils.isEmpty(mName) || TextUtils.isEmpty(password)) {
                    Toast.makeText(context, "您的用户部门，名称或密码不能为空！", Toast.LENGTH_LONG).show();
                } else { // 再判断密码是否正确
                    PasswordChecked(mName, password);
                    if (flag.equals("true")) {
                        myListener.getSpinnerText(mName);
                        mDialog.cancel();
                    } else {
                        Toast.makeText(context, "您输入的密码有误，请重新输入", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        //取消签名按钮
        bt_cancle_qm.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String password1 = et_pw.getText().toString().trim();
                if (TextUtils.isEmpty(mClub) || TextUtils.isEmpty(mName) || TextUtils.isEmpty(password1)) {
                    Toast.makeText(context, "您的用户部门，名称或密码不能为空！", Toast.LENGTH_LONG).show();
                } else { // 再判断密码是否正确
                    PasswordChecked(mName, password1);
                    if (flag.equals("true")) {
                        myListener.getSpinnerText("");
                        mDialog.cancel();
                    } else {
                        Toast.makeText(context, "您输入的密码有误，请重新输入", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        this.mDialog.setContentView(view);
        this.mDialog.setCanceledOnTouchOutside(true);
        this.mDialog.show();

    }

    public interface MyListener {
        void getSpinnerText(String userName);
    }

    /*
* 请求网络获取签名人员列表数据*/
    public void getSignatureData() {
//        if (objId == null && mbId == null) {
//            Toast.makeText(context, "用户id为空，请重新登陆", Toast.LENGTH_SHORT).show();
//        } else {
//            StringBuilder sb = new StringBuilder();
//            //拼接请求体
//            sb.append("<list>").append("\n")
//                    .append("<params>").append("\n")
//                    .append("<PID>").append(objId).append("</PID>").append("\n")
//                    .append("<MBID>").append("16").append("</MBID>").append("\n")
//                    .append("<QMYJMC>").append("监护人").append("</QMYJMC>").append("\n")
//                    .append("<BMID>").append("40288188443e602001446dd0d3271812").append("</BMID>").append("\n")
//                    .append("<MKLX>").append("2").append("</MKLX>").append("\n")
//                    .append("</params>").append("\n")
//                    .append("</list>");
//            String serviceName = "sxlp1"; //   服务名
//            String interfaceName = "signName";//   接口名
//            Object[] params = new Object[]{sb};
//            String data = DataReadUtil.getDataFromDb(serviceName, interfaceName, params);
//            Log.e("lala", "CustomSignatureDialog   data======" + data);
//        }
    }

    /*
* 签名密码验证*/
    public void PasswordChecked(String YHMC, String pwd) {
//        StringBuilder sb = new StringBuilder();
//        //拼接请求体
//        sb.append("<list>").append("\n")
//                .append("<params>").append("\n")
//                .append("<LOGIN_NAME>").append(YHMC).append("</LOGIN_NAME>").append("\n")
//                .append("<PASS_WORD>").append(pwd).append("</PASS_WORD>").append("\n")
//                .append("</params>").append("\n")
//                .append("</list>");
//        String serviceName = "sxlp1"; //   服务名
//        String interfaceName = "validateUserPass";//   接口名
//        Object[] params = new Object[]{sb};
//        String data = DataReadUtil.getDataFromDb(serviceName, interfaceName, params);
//        Log.e("lala", "mainactivity   data======" + data);
//        parsePasswordChecked(data);
    }

    /**
     * 解析签名密码验证数据
     *
     * @author DWQ
     */
    private void parsePasswordChecked(String t) {
        if (t == null && "".equals(t)) {
            //  Toast.makeText(getActivity(), "没有更多数据了", Toast.LENGTH_SHORT).show();
            Log.e("lala", "走了t == null &&.equals(t)=====" + t);
        } else {
            JSONObject data;
            try {
                data = new JSONObject(t);
                JSONArray records = data.getJSONArray("records");
                if (records.length() == 0) {
                    Toast.makeText(context, "没有更多数据了", Toast.LENGTH_SHORT).show();
                    Log.e("lala", "records.length() == 0=====" + records.length());
                } else {
                    for (int i = 0; i < records.length(); i++) {
                        JSONObject record = records.getJSONObject(i);
                        flag = record.getString("FLAG");
                        if ("null".equals(flag)) {
                            flag = "false";
                        }
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();

            }
        }
    }


    public void show() {
        this.mDialog.show();
    }

    // 设置标题
    public void setTitle(String title) {
        tv_qm_title.setText(title);
    }
}
