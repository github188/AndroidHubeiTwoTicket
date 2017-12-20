package tickets.nari.com.hubeitickets.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import tickets.nari.com.hubeitickets.R;


/**
 * 执行中界面
 */

public class ExecutionActivity extends FragmentActivity implements View.OnClickListener {
    //返回
    private ImageView back;
    //操作状态
    private TextView no1, moren1, yes1, no2, moren2, yes2, no3, moren3, yes3, no4, moren4, yes4;
    private LinearLayout caozuostate1, caozuostate2, caozuostate3, caozuostate4;
    //调度令，暂停，开始，终结，保存
    private LinearLayout diaodu, paused, start, end, save;
    //顶部popupwindow
//    private ImageView popup_up;
    //中间popupwindow
    private ImageView popup_down;
    //上下文
    private Context mContext;
    private TextView mTv_exe_bzd_address;   //   变电站地址
    private TextView mTv_exe_pd;    //   调令类型
    private TextView mTv_exe_dl_num;    //   调令号
    private TextView mTv_exe_ticket_num;  //   票号
    private TextView mTv_exe_flr_name;//   发令人名称
    private TextView mTv_exe_jlr_name;//   接令人名称
    private TextView mTv_exe_fl_time;    //   发令时间
    private TextView mTv_exe_czks_time;    //   操作开始时间
    private TextView mTv_exe_czjs_time;  //   操作结束时间
    private TextView mTv_exe_czrw;    //   操作任务
    private TextView mTv_exe_order_one;   //   序号1
    private TextView mTv_exe_czxm_one;   //   操作项目1
    private TextView mTv_exe_zxsj_one;  //   执行时间1
    private TextView mTv_exe_order_two;  //   序号2
    private TextView mTv_exe_czxm_two;  //   操作项目2
    private TextView mTv_exe_zxsj_two;  //   执行时间2
    private TextView mTv_exe_order_three;   //   序号3
    private TextView mTv_exe_czxm_three; //   操作项目3
    private TextView mTv_exe_zxsj_three;  //   执行时间3
    private TextView mTv_exe_order_four;  //   序号4
    private TextView mTv_exe_czxm_four;  //   操作项目4
    private TextView mTv_exe_zxsj_four;  //   执行时间4
    private TextView mTv_exe_bz;   //   备注
    private TextView mTv_exe_qm_tpr; //   填票人
    private TextView mTv_exe_qm_spr; //   审票人
    private TextView mTv_exe_qm_fzr; //   负责人
    private TextView mTv_exe_qm_czr;  //   操作人
    private TextView mTv_exe_qm_jhr; //   监护人
    private RelativeLayout rl_exe_qm_tpr;
    private RelativeLayout rl_exe_qm_spr;
    private RelativeLayout rl_exe_qm_fzr;
    private RelativeLayout rl_exe_qm_czr;
    private RelativeLayout rl_exe_qm_jhr;
    private TextView mTv_exe_qm_tpsj; //   填票时间
    private CheckBox mCb_exe_left;//   监护下操作
    private CheckBox mCb_exe_center; //   单人操作
    private CheckBox mCb_exe_right; //   检修人员操作
    private TextView mTv_exe_look_all; // 查看全部
//    private CustomListView lv_exe_czxm;
//    private ExecutionActivityAdapter mExecutionActivityAdapter;//   步骤适配器
    private boolean mIsSplsh = true;  // 默认滑动初始值
    private String[] mCzbzArray;
    public RelativeLayout re_exe_title;
    private ScrollView mSl_exe;
    boolean jianhu, danren, jianxiu;// checkbox选择状态
    private String objId;
    private String mbId;
    private String mRYMC;
    private String mUserId;
//    private TimePickerView pvCustomTime;
    private int mTimeStyle;   //   用于区分是那个时间按钮 1：发令时间 ； 2：操作开始时间 ； 3： 操作结束时间；
    private Date mDate1; //   操作开始时间

    private RadioGroup czRg;
    private RadioButton jkRb,drRb,jxRb;
    private TextView kssj,jssj;
    private TextView czr_xm,jhr_xm,ywfzr_xm,jkTv,drTv,jxTv;
    private ImageView czr_right,jhr_right,ywfzr_right;
    private TextView top_name,czdw,bh;

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
        setContentView(R.layout.execution_ticket);
//        init();
    }


    /**
     * 初始化控件
     */
    private void init() {
        re_exe_title = findViewById(R.id.re_exe_title);
//        diaodu = (LinearLayout) findViewById(R.id.ll_exe_activity_diaodu);
//        paused = (LinearLayout) findViewById(R.id.ll_execution_activity_paused);
//        start = (LinearLayout) findViewById(R.id.ll_execution_activity_start);
//        end = (LinearLayout) findViewById(R.id.ll_execution_activity_end);
//        save = (LinearLayout) findViewById(R.id.ll_execution_activity_save);
//        popup_up = (ImageView) findViewById(R.id.image_execution_activity_upPopup);
//        popup_down = (ImageView) findViewById(R.id.image_execution_activity_downPopup);
        back = (ImageView) findViewById(R.id.image_execution_activity_back);
//        caozuostate1 = (LinearLayout) findViewById(R.id.ll_execution_activity_caozuo1);
//        caozuostate2 = (LinearLayout) findViewById(R.id.ll_execution_activity_caozuo2);
//        caozuostate3 = (LinearLayout) findViewById(R.id.ll_execution_activity_caozuo3);
//        caozuostate4 = (LinearLayout) findViewById(R.id.ll_execution_activity_caozuo4);
//        no1 = (TextView) findViewById(R.id.tv_execution_activity_no1);
//        moren1 = (TextView) findViewById(R.id.tv_execution_activity_moren1);
//        yes1 = (TextView) findViewById(R.id.tv_execution_activity_yes1);
//        no2 = (TextView) findViewById(R.id.tv_execution_activity_no2);
//        moren2 = (TextView) findViewById(R.id.tv_execution_activity_moren2);
//        yes2 = (TextView) findViewById(R.id.tv_execution_activity_yes2);
//        no3 = (TextView) findViewById(R.id.tv_execution_activity_no3);
//        moren3 = (TextView) findViewById(R.id.tv_execution_activity_moren3);
//        yes3 = (TextView) findViewById(R.id.tv_execution_activity_yes3);
//        no4 = (TextView) findViewById(R.id.tv_execution_activity_no4);
//        moren4 = (TextView) findViewById(R.id.tv_execution_activity_moren4);
//        yes4 = (TextView) findViewById(R.id.tv_execution_activity_yes4);
//        mTv_exe_bzd_address = findViewById(R.id.tv_exe_bzd_address);
//        mTv_exe_pd = findViewById(R.id.tv_exe_pd);
//        mTv_exe_dl_num = findViewById(R.id.tv_exe_dl_num);
//        mTv_exe_ticket_num = findViewById(R.id.tv_exe_ticket_num);
//        mTv_exe_flr_name = findViewById(R.id.tv_exe_flr_name);
//        mTv_exe_jlr_name = findViewById(R.id.tv_exe_jlr_name);
//        mTv_exe_fl_time = findViewById(R.id.tv_exe_fl_time);
//        mTv_exe_czks_time = findViewById(R.id.tv_exe_czks_time);
//        mTv_exe_czjs_time = findViewById(R.id.tv_exe_czjs_time);
        mTv_exe_czrw = findViewById(R.id.tv_exe_czrw);
//        mTv_exe_order_one = findViewById(R.id.tv_exe_order_one);
//        mTv_exe_czxm_one = findViewById(R.id.tv_exe_czxm_one);
//        mTv_exe_zxsj_one = findViewById(R.id.tv_exe_zxsj_one);
//        mTv_exe_order_two = findViewById(R.id.tv_exe_order_two);
//        mTv_exe_czxm_two = findViewById(R.id.tv_exe_czxm_two);
//        mTv_exe_zxsj_two = findViewById(R.id.tv_exe_zxsj_two);
//        mTv_exe_order_three = findViewById(R.id.tv_exe_order_three);
//        mTv_exe_czxm_three = findViewById(R.id.tv_exe_czxm_three);
//        mTv_exe_zxsj_three = findViewById(R.id.tv_exe_zxsj_three);
//        mTv_exe_order_four = findViewById(R.id.tv_exe_order_four);
//        mTv_exe_czxm_four = findViewById(R.id.tv_exe_czxm_four);
//        mTv_exe_zxsj_four = findViewById(R.id.tv_exe_zxsj_four);
        mTv_exe_bz = findViewById(R.id.tv_exe_bz);
//        mTv_exe_qm_tpr = findViewById(R.id.tv_exe_qm_tpr);
//        mTv_exe_qm_spr = findViewById(R.id.tv_exe_qm_spr);
//        mTv_exe_qm_fzr = findViewById(R.id.tv_exe_qm_fzr);
//        mTv_exe_qm_czr = findViewById(R.id.tv_exe_qm_czr);
//        mTv_exe_qm_jhr = findViewById(R.id.tv_exe_qm_jhr);
//        mTv_exe_qm_tpsj = findViewById(R.id.tv_exe_qm_tpsj);
//        rl_exe_qm_tpr = findViewById(R.id.rl_exe_qm_tpr);
//        rl_exe_qm_spr = findViewById(R.id.rl_exe_qm_spr);
//        rl_exe_qm_fzr = findViewById(R.id.rl_exe_qm_fzr);
//        rl_exe_qm_czr = findViewById(R.id.rl_exe_qm_czr);
//        rl_exe_qm_jhr = findViewById(R.id.rl_exe_qm_jhr);
//        mCb_exe_left = findViewById(R.id.cb_exe_left);
//        mCb_exe_center = findViewById(R.id.cb_exe_center);
//        mCb_exe_right = findViewById(R.id.cb_exe_right);
        mTv_exe_look_all = findViewById(R.id.tv_exe_look_all);
//        lv_exe_czxm = findViewById(R.id.lv_exe_czxm);
//        mSl_exe = findViewById(R.id.sl_exe);

        mTv_exe_look_all = findViewById(R.id.tv_exe_look_all);

        czRg = findViewById(R.id.cz_rg);
        jkRb = findViewById(R.id.cb_jk);
        drRb = findViewById(R.id.cb_dr);
        jxRb = findViewById(R.id.cb_jx);
//        jkTv = findViewById(R.id.tv_jk);
//        drTv = findViewById(R.id.tv_dr);
//        jxTv = findViewById(R.id.tv_jx);
        czr_xm = findViewById(R.id.czr_xm);
        jhr_xm = findViewById(R.id.jhr_xm);
        ywfzr_xm = findViewById(R.id.ywfzr_xm);
        czr_right = findViewById(R.id.czr_right);
        jhr_right = findViewById(R.id.jhr_right);
        ywfzr_right = findViewById(R.id.ywfzr_right);
//        kssj = findViewById(R.id.tv_arch_czks_time);
//        jssj = findViewById(R.id.tv_arch_czjs_time);
        top_name = findViewById(R.id.tv_check_top_name);
        bh = findViewById(R.id.check_bh);
        czdw = findViewById(R.id.check_czdw);

        czRg.check(R.id.cb_jk);
        jianhu = true;

        objId = getIntent().getStringExtra("obj_id");
        mbId = getIntent().getStringExtra("mbId");
//        SharedPreferencesHelper sharedPreferencesHelper = new SharedPreferencesHelper(getApplicationContext(), "PersonalInformation");
//        mRYMC = sharedPreferencesHelper.getStringValue("userName");
//        mUserId = sharedPreferencesHelper.getStringValue("userId");

        initListener();
        getTicketDetailsData();
    }

    private void initListener() {
//        initCustomTimePicker();
//        diaodu.setOnClickListener(this);
//        popup_up.setOnClickListener(this);
//        popup_down.setOnClickListener(this);
//        diaodu.setOnClickListener(this);
//        paused.setOnClickListener(this);
//        start.setOnClickListener(this);
//        end.setOnClickListener(this);
//        save.setOnClickListener(this);
//        back.setOnClickListener(this);
//        no1.setOnClickListener(this);
//        moren1.setOnClickListener(this);
//        yes1.setOnClickListener(this);
//        no2.setOnClickListener(this);
//        moren2.setOnClickListener(this);
//        yes2.setOnClickListener(this);
//        no3.setOnClickListener(this);
//        moren3.setOnClickListener(this);
//        yes3.setOnClickListener(this);
//        no4.setOnClickListener(this);
//        moren4.setOnClickListener(this);
//        yes4.setOnClickListener(this);
//        rl_exe_qm_spr.setOnClickListener(this);
//        rl_exe_qm_tpr.setOnClickListener(this);
//        rl_exe_qm_fzr.setOnClickListener(this);
//        rl_exe_qm_czr.setOnClickListener(this);
//        rl_exe_qm_jhr.setOnClickListener(this);
//        mTv_exe_look_all.setOnClickListener(this);
//        mTv_exe_fl_time.setOnClickListener(this);
//        mTv_exe_czks_time.setOnClickListener(this);
//        mTv_exe_czjs_time.setOnClickListener(this);
//        mCb_exe_left.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                mCb_exe_center.setChecked(false);
//                mCb_exe_right.setChecked(false);
//                mCb_exe_left.setChecked(b);
//                jianhu = b;
//            }
//        });
//
//        mCb_exe_center.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                mCb_exe_left.setChecked(false);
//                mCb_exe_right.setChecked(false);
//                mCb_exe_center.setChecked(b);
//                danren = b;
//                if (b == true) {
//                    mTv_exe_qm_jhr.setText("(空)");
//                    mTv_exe_qm_jhr.setClickable(false);
//                } else {
//                    mTv_exe_qm_jhr.setText("");
//                    mTv_exe_qm_jhr.setClickable(true);
//                }
//            }
//        });
//
//        mCb_exe_right.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                mCb_exe_left.setChecked(false);
//                mCb_exe_center.setChecked(false);
//                mCb_exe_right.setChecked(b);
//                jianxiu = b;
//            }
//        });
//        mSl_exe.setOnScrollChangeListener(new View.OnScrollChangeListener() {
//            @Override
//            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
//                if (mIsSplsh) {
//                    if (i1 > 4424) {
//                        mExecutionActivityAdapter.setCount(mCzbzArray.length);
//                        mExecutionActivityAdapter.notifyDataSetChanged();
//                        mIsSplsh = false;
//                    }
//                }
//
//            }
//        });
//        lv_exe_czxm.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Log.e("lala", "lv_exe_czxm.getChildCount()============" + lv_exe_czxm.getChildCount());
////                RiskPopWin riskPopWin = new RiskPopWin(ExecutionActivity.this);
////                if (riskPopWin.isShowing()) {
////                    riskPopWin.dismiss();
////                }
////
////                riskPopWin.showAsDropDown(re_exe_title, Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
//            }
//        });
//
//        lv_exe_czxm.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//                TextView tv_exe_czxm_one = view.findViewById(R.id.tv_exe_czxm_one);
//                String str = tv_exe_czxm_one.getText().toString().trim();
//                if (str.indexOf("(") != -1) {   //    != -1 位包含该字符
//                    // mExecutionActivityAdapter.updateItemView(i, true, "长按了第" + i);
//                    CusDialogDixian cusDialogDixian = new CusDialogDixian(ExecutionActivity.this, mExecutionActivityAdapter, i, str);
//                    cusDialogDixian.show();
//                } else {
//                    Toast.makeText(ExecutionActivity.this, "该步骤不可编辑", Toast.LENGTH_SHORT).show();
//                }
//
//                return true;
//            }
//        });
//
//        czr_right.setOnClickListener(this);
//        jhr_right.setOnClickListener(this);
//        ywfzr_right.setOnClickListener(this);
//
//        czRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, int i) {
//                switch (i){
//                    case R.id.cb_jk:
//                        jianhu = true;
//                        danren = false;
//                        jianxiu = false;
//
//                        jkTv.setTextColor(Color.parseColor("#07abe8"));
//                        drTv.setTextColor(Color.parseColor("#333333"));
//                        jxTv.setTextColor(Color.parseColor("#333333"));
//                        break;
//                    case R.id.cb_dr:
//                        jianhu = false;
//                        danren = true;
//                        jianxiu = false;
//
//                        jkTv.setTextColor(Color.parseColor("#333333"));
//                        drTv.setTextColor(Color.parseColor("#07abe8"));
//                        jxTv.setTextColor(Color.parseColor("#333333"));
//                        break;
//                    case R.id.cb_jx:
//                        jianhu = false;
//                        danren = false;
//                        jianxiu = true;
//
//                        jkTv.setTextColor(Color.parseColor("#333333"));
//                        drTv.setTextColor(Color.parseColor("#333333"));
//                        jxTv.setTextColor(Color.parseColor("#07abe8"));
//                        break;
//                }
//            }
//        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.image_execution_activity_back://返回按钮
//                finish();
//                break;
//            case R.id.tv_execution_activity_no1://操作项目未执行状态
//                caozuostate1.setBackgroundResource(R.mipmap.caozuo_no);
//                break;
//            case R.id.tv_execution_activity_moren1://操作项目默认状态
//                caozuostate1.setBackgroundResource(R.mipmap.caozuo_moren);
//                break;
//            case R.id.tv_execution_activity_yes1://操作项目已执行状态
//                caozuostate1.setBackgroundResource(R.mipmap.caozuo_yes);
//                break;
//            case R.id.tv_execution_activity_no2:
//                caozuostate2.setBackgroundResource(R.mipmap.caozuo_no);
//                break;
//            case R.id.tv_execution_activity_moren2:
//                caozuostate2.setBackgroundResource(R.mipmap.caozuo_moren);
//                break;
//            case R.id.tv_execution_activity_yes2:
//                caozuostate2.setBackgroundResource(R.mipmap.caozuo_yes);
//                break;
//            case R.id.tv_execution_activity_no3:
//                caozuostate3.setBackgroundResource(R.mipmap.caozuo_no);
//                break;
//            case R.id.tv_execution_activity_moren3:
//                caozuostate3.setBackgroundResource(R.mipmap.caozuo_moren);
//                break;
//            case R.id.tv_execution_activity_yes3:
//                caozuostate3.setBackgroundResource(R.mipmap.caozuo_yes);
//                break;
//            case R.id.tv_execution_activity_no4:
//                caozuostate4.setBackgroundResource(R.mipmap.caozuo_no);
//                break;
//            case R.id.tv_execution_activity_moren4:
//                caozuostate4.setBackgroundResource(R.mipmap.caozuo_moren);
//                break;
//            case R.id.tv_execution_activity_yes4:
//                caozuostate4.setBackgroundResource(R.mipmap.caozuo_yes);
//                break;
//            case R.id.rl_exe_qm_tpr://填票人
////                if (danren == false && jianhu == false && jianxiu == false) {
////                    Toast.makeText(getApplicationContext(), "必须选择一种操作模式", Toast.LENGTH_SHORT).show();
////                } else {
////                    CustomSignatureDialog dialog1 = new CustomSignatureDialog(ExecutionActivity.this, objId, mbId, new CustomSignatureDialog.MyListener() {
////                        @Override
////                        public void getSpinnerText(String userName) {
////                            String tpr = userName;
////                            mTv_exe_qm_tpr.setText(tpr);
////                        }
////                    });
////                    dialog1.setTitle("填票人签名");
////                }
//                //      break;
//            case R.id.rl_exe_qm_spr://审票人
////                if (danren == false && jianhu == false && jianxiu == false) {
////                    Toast.makeText(getApplicationContext(), "必须选择一种操作模式", Toast.LENGTH_SHORT).show();
////                } else {
////                    CustomSignatureDialog dialog2 = new CustomSignatureDialog(ExecutionActivity.this, objId, mbId, new CustomSignatureDialog.MyListener() {
////                        @Override
////                        public void getSpinnerText(String userName) {
////                            String spr = userName;
////                            mTv_exe_qm_spr.setText(spr);
////                        }
////                    });
////                    dialog2.setTitle("审票人签名");
////                }
////                break;
//            case R.id.rl_exe_qm_fzr://负责人
//                if (danren == false && jianhu == false && jianxiu == false) {
//                    Toast.makeText(getApplicationContext(), "必须选择一种操作模式", Toast.LENGTH_SHORT).show();
//                } else {
//                    CustomSignatureDialog dialog3 = new CustomSignatureDialog(ExecutionActivity.this, objId, mbId, new CustomSignatureDialog.MyListener() {
//                        @Override
//                        public void getSpinnerText(String userName) {
//                            String fzr = userName;
//                            mTv_exe_qm_fzr.setText(fzr);
//                        }
//                    });
//                    dialog3.setTitle("负责人签名");
//                }
//                break;
//            case R.id.rl_exe_qm_czr://操作人
//                if (danren == false && jianhu == false && jianxiu == false) {
//                    Toast.makeText(getApplicationContext(), "必须选择一种操作模式", Toast.LENGTH_SHORT).show();
//                } else {
//                    CustomSignatureDialog dialog4 = new CustomSignatureDialog(ExecutionActivity.this, objId, mbId, new CustomSignatureDialog.MyListener() {
//                        @Override
//                        public void getSpinnerText(String userName) {
//                            String czr = userName;
//                            mTv_exe_qm_czr.setText(czr);
//                        }
//                    });
//                    dialog4.setTitle("操作人签名");
//                }
//                break;
//            case R.id.rl_exe_qm_jhr://监护人
//                if (danren == false && jianhu == false && jianxiu == false) {
//                    Toast.makeText(getApplicationContext(), "必须选择一种操作模式", Toast.LENGTH_SHORT).show();
//                } else if (jianhu == true || jianxiu == true) {
//                    CustomSignatureDialog dialog5 = new CustomSignatureDialog(ExecutionActivity.this, objId, mbId, new CustomSignatureDialog.MyListener() {
//                        @Override
//                        public void getSpinnerText(String userName) {
//                            String jhr = userName;
//                            mTv_exe_qm_jhr.setText(jhr);
//                        }
//                    });
//                    dialog5.setTitle("监护人签名");
//                }
//                break;
//            case R.id.ll_execution_activity_paused://暂停
//                Toast.makeText(getApplicationContext(), "点击了暂停······", Toast.LENGTH_SHORT).show();
//                getCzpIsNotPause("pause");
//                break;
//            case R.id.ll_execution_activity_start://开始
//                Toast.makeText(getApplicationContext(), "点击了开始······", Toast.LENGTH_SHORT).show();
//                getCzpIsNotPause("start");
//                break;
//            case R.id.ll_execution_activity_end://终结
//                if (mRYMC == null || "".equals(mRYMC) || mUserId == null || "".equals(mUserId)) {
//                    Toast.makeText(getApplicationContext(), "您的用户名或用户id为空", Toast.LENGTH_SHORT).show();
//                } else {
//                    toEndCZP();
//                }
//
//
//                break;
//            case R.id.ll_execution_activity_save://保存
//                if (mRYMC == null || "".equals(mRYMC) || mUserId == null || "".equals(mUserId)) {
//                    Toast.makeText(getApplicationContext(), "您的用户名或用户id为空", Toast.LENGTH_SHORT).show();
//                } else {
//                    saveCZPInfo();
//                }
//
//                Toast.makeText(getApplicationContext(), "点击了保存······", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.image_execution_activity_upPopup://顶部菜单
//                UpPopupWindow upPopupWindow = new UpPopupWindow(ExecutionActivity.this, popup_up);
//                break;
//            case R.id.image_execution_activity_downPopup://操作项目菜单
//                DownPopupWindow downPopupWindow = new DownPopupWindow(ExecutionActivity.this, popup_down);
//                break;
//            case R.id.tv_exe_look_all:  //   查看全部
////                Intent intent = new Intent(ExecutionActivity.this, OperationStepsActivity.class);
////                startActivity(intent);
//                break;
//            case R.id.ll_exe_activity_diaodu://调度令
//                Intent intent1 = new Intent();
//                intent1.setClass(ExecutionActivity.this, OrderActivity.class);
//                startActivity(intent1);
//                break;
////            case R.id.tv_exe_czks_time:
////                mTimeStyle = 2;
////                pvCustomTime.show();
////
////                break;
//            case R.id.tv_exe_fl_time:
//                mTimeStyle = 1;
//                pvCustomTime.show();
//
//                break;
////            case R.id.tv_exe_czjs_time:
////                mTimeStyle = 3;
////                pvCustomTime.show();
////                break;
//            case R.id.czr_right:
//                if (danren == false && jianhu == false && jianxiu == false) {
//                    Toast.makeText(getApplicationContext(), "必须选择一种操作模式", Toast.LENGTH_SHORT).show();
//                } else {
//                    CustomSignatureDialog dialog3 = new CustomSignatureDialog(ExecutionActivity.this, objId, mbId, new CustomSignatureDialog.MyListener() {
//                        @Override
//                        public void getSpinnerText(String userName) {
//                            String fzr = userName;
//                            czr_xm.setText(fzr);
//                        }
//                    });
//                    dialog3.setTitle("操作人签名");
//                }
//                break;
//            case R.id.jhr_right:
//                if (danren == false && jianhu == false && jianxiu == false) {
//                    Toast.makeText(getApplicationContext(), "必须选择一种操作模式", Toast.LENGTH_SHORT).show();
//                } else {
//                    CustomSignatureDialog dialog3 = new CustomSignatureDialog(ExecutionActivity.this, objId, mbId, new CustomSignatureDialog.MyListener() {
//                        @Override
//                        public void getSpinnerText(String userName) {
//                            String fzr = userName;
//                            jhr_xm.setText(fzr);
//                        }
//                    });
//                    dialog3.setTitle("监护人签名");
//                }
//                break;
//            case R.id.ywfzr_right:
//                if (danren == false && jianhu == false && jianxiu == false) {
//                    Toast.makeText(getApplicationContext(), "必须选择一种操作模式", Toast.LENGTH_SHORT).show();
//                } else {
//                    CustomSignatureDialog dialog3 = new CustomSignatureDialog(ExecutionActivity.this, objId, mbId, new CustomSignatureDialog.MyListener() {
//                        @Override
//                        public void getSpinnerText(String userName) {
//                            String fzr = userName;
//                            ywfzr_xm.setText(fzr);
//                        }
//                    });
//                    dialog3.setTitle("运维负责人签名");
//                }
//                break;
//            default:
//                break;
        }
    }

    /*判断当前票状态是否为暂停态*/
    private void getCzpIsNotPause(String stytle) {
//        StringBuilder sb = new StringBuilder();
//        //拼接请求体
//        sb.append("<list>").append("\n")
//                .append("<params>").append("\n")
//                .append("<PID>")  //票id
//                .append(objId)
//                .append("</PID>").append("\n")
//                .append("</params>").append("\n")
//                .append("</list>");
//        String serviceName = "sxlp1"; //   服务名
//        String interfaceName = "getCzpIsNotPause";//   接口名
//        Object[] params = new Object[]{sb};
//        String data = DataReadUtil.getDataFromDb(serviceName, interfaceName, params);
//        if (data == null && "".equals(data)) {
//            Toast.makeText(ExecutionActivity.this, "请检查网络", Toast.LENGTH_SHORT).show();
//        } else {
//            JSONObject datas;
//            try {
//                datas = new JSONObject(data);
//                JSONArray records = datas.getJSONArray("records");
//                if (records.length() == 0) {
//                    Toast.makeText(ExecutionActivity.this, "数据为空", Toast.LENGTH_SHORT).show();
//                } else {
//                    Log.e("lala", "records=======" + records.toString());
//                    JSONObject record = records.getJSONObject(0);
//                    //工作票主键
//                    String flag = record.getString("FLAG");
//                    if ("true".equals(flag)) {
//
//                        if ("start".equals(stytle)) {
//                            toStartCzp();
//                        }else {
//                            Toast.makeText(ExecutionActivity.this, "当前是暂停状态", Toast.LENGTH_SHORT).show();
//                        }
//
//                    } else {
//                        Log.e("lala","当前不是暂停状态");
//                        if ("pause".equals(stytle)) {
//                            toPauseCzp();
//                        }else {
//                            Toast.makeText(ExecutionActivity.this, "当前不在暂停状态", Toast.LENGTH_SHORT).show();
//                        }
//
//                    }
//                }
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//
//            }
//        }
    }

    /*17、将操作票由暂停态变为执行态接口*/
    private void toStartCzp() {
//        StringBuilder sb = new StringBuilder();
//        //拼接请求体
//        sb.append("<list>").append("\n")
//                .append("<params>").append("\n")
//                .append("<PID>")  //票id
//                .append(objId)
//                .append("</PID>").append("\n")
//                .append("</params>").append("\n")
//                .append("</list>");
//        String serviceName = "sxlp1"; //   服务名
//        String interfaceName = "toStartCzp";//   接口名
//        Object[] params = new Object[]{sb};
//        String data = DataReadUtil.getDataFromDb(serviceName, interfaceName, params);
//        if (data == null && "".equals(data)) {
//            Toast.makeText(ExecutionActivity.this, "请检查网络", Toast.LENGTH_SHORT).show();
//        } else {
//            JSONObject datas;
//            try {
//                datas = new JSONObject(data);
//                JSONArray records = datas.getJSONArray("records");
//                if (records.length() == 0) {
//                    Toast.makeText(ExecutionActivity.this, "数据为空", Toast.LENGTH_SHORT).show();
//                } else {
//                    Log.e("lala", "records=======" + records.toString());
//                    JSONObject record = records.getJSONObject(0);
//                    //工作票主键
//                    String flag = record.getString("FLAG");
//                    if ("true".equals(flag)) {
//                        Toast.makeText(ExecutionActivity.this, "执行成功", Toast.LENGTH_SHORT).show();
//
//                    } else {
//                        Toast.makeText(ExecutionActivity.this, "执行失败", Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//
//            }
//        }
    }

    /*暂停操作步骤*/
    private void toPauseCzp() {
//        StringBuilder sb = new StringBuilder();
//        //拼接请求体
//        sb.append("<list>").append("\n")
//                .append("<params>").append("\n")
//                .append("<PID>")  //票id
//                .append(objId)
//                .append("</PID>").append("\n")
//                .append("</params>").append("\n")
//                .append("</list>");
//        String serviceName = "sxlp1"; //   服务名
//        String interfaceName = "toPauseCzp";//   接口名
//        Object[] params = new Object[]{sb};
//        String data = DataReadUtil.getDataFromDb(serviceName, interfaceName, params);
//        if (data == null && "".equals(data)) {
//            Toast.makeText(ExecutionActivity.this, "请检查网络", Toast.LENGTH_SHORT).show();
//        } else {
//            JSONObject datas;
//            try {
//                datas = new JSONObject(data);
//                JSONArray records = datas.getJSONArray("records");
//                if (records.length() == 0) {
//                    Toast.makeText(ExecutionActivity.this, "数据为空", Toast.LENGTH_SHORT).show();
//                } else {
//                    Log.e("lala", "records=======" + records.toString());
//                    JSONObject record = records.getJSONObject(0);
//                    //工作票主键
//                    String flag = record.getString("FLAG");
//                    if ("true".equals(flag)) {
//                        Toast.makeText(ExecutionActivity.this, "暂停成功", Toast.LENGTH_SHORT).show();
//
//                    } else {
//                        Toast.makeText(ExecutionActivity.this, "暂停失败", Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//
//            }
//        }
    }

    /*
    * 发送操作票终结消息*/
    private void toEndCZP() {
//        String finishName = "终结|终结人：" + mRYMC;
//        StringBuilder sb = new StringBuilder();
//        //拼接请求体
//        sb.append("<list>").append("\n")
//                .append("<params>").append("\n")
//                .append("<USER_NAME>>")  //人员名称
//                .append(mRYMC)
//                .append("</USER_NAME>>").append("\n")
//                .append("<USER_ID>")  //用户id
//                .append(mUserId)
//                .append("</USER_ID>").append("\n")
//                .append("<PID>")  //票id
//                .append(objId)
//                .append("</PID>").append("\n")
//                .append("<PZT>")   //票状态
//                .append("51")
//                .append("</PZT>").append("\n")
//                .append("<LOG_INFO>")  //
//                .append(finishName)
//                .append("</LOG_INFO>").append("\n")
//                .append("<CZLX>")
//                .append("终结")
//                .append("</CZLX>").append("\n")
//                .append("<MKLX>")
//                .append("2")
//                .append("</MKLX>").append("\n")
//                .append("<MBID>")
//                .append(mbId)
//                .append("</MBID>").append("\n")
//                .append("</params>").append("\n")
//                .append("</list>");
//        String serviceName = "sxlp1"; //   服务名
//        String interfaceName = "toEndCZP";//   接口名
//        Object[] params = new Object[]{sb};
//        String data = DataReadUtil.getDataFromDb(serviceName, interfaceName, params);
//        Log.e("lala", "点击终结按钮后的数据===============" + data);
//        if (data == null && "".equals(data)) {
//            Toast.makeText(ExecutionActivity.this, "请检查网络", Toast.LENGTH_SHORT).show();
//        } else {
//            JSONObject datas;
//            try {
//                datas = new JSONObject(data);
//                JSONArray records = datas.getJSONArray("records");
//                if (records.length() == 0) {
//                    Toast.makeText(ExecutionActivity.this, "数据为空", Toast.LENGTH_SHORT).show();
//                } else {
//                    Log.e("lala", "records=======" + records.toString());
//                    JSONObject record = records.getJSONObject(0);
//                    //工作票主键
//                    String flag = record.getString("FLAG");
//                    if ("true".equals(flag)) {
//                        Toast.makeText(ExecutionActivity.this, "终结成功", Toast.LENGTH_SHORT).show();
//                        finish();
//                    } else {
//                        Toast.makeText(ExecutionActivity.this, "终结失败", Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//
//            }
//        }
    }

    /*
 * 请求网络获取票详情页数据*/
    public void getTicketDetailsData() {
//        if (objId == null || mbId == null || "".equals(objId) || "".equals(mbId)) {
//            Toast.makeText(ExecutionActivity.this, "用户id为空，请重新登陆", Toast.LENGTH_SHORT).show();
//        } else {
//            StringBuilder sb = new StringBuilder();
//            //拼接请求体
//            sb.append("<list>").append("\n")
//                    .append("<params>").append("\n")
//                    .append("<PID>")
//                    .append(objId)
//                    .append("</PID>").append("\n")
//                    .append("<MBID>")
//                    .append(mbId)
//                    .append("</MBID>").append("\n")
//                    .append("</params>").append("\n")
//                    .append("</list>");
//            String serviceName = "sxlp1"; //   服务名
//            String interfaceName = "getCZPInfo";//   接口名
//            Object[] params = new Object[]{sb};
//            String data = DataReadUtil.getDataFromDb(serviceName, interfaceName, params);
//            Log.e("lala", "checktickactivity   data======" + data);
//         //   setData(data);
//        }
    }

    /*
    * 设置数据*/
    private void setData(String data) {
//        ExecutionTicketActivityBean dataBean = JSON.parseObject(data, ExecutionTicketActivityBean.class);
//        ExecutionTicketActivityBean.RecordsBean recordsBean = dataBean.getRecords().get(0);
//        String status = recordsBean.getYJWB(); //   用于判断选择框的状态
//        String allCzbz = recordsBean.getHWB();
//        Log.e("lala","操作步骤============" + allCzbz);
//       mCzbzArray = StringUtil.splitString("@@", allCzbz);
//        Log.e("lala","executionactivity界面mCzbzArray============" + mCzbzArray.length);
//        String cb_left = recordsBean.getJHXCZ();   //   监护下操作
//        String cb_center = recordsBean.getDRCZ();   //   单人操作
//        String cb_right = recordsBean.getJXRYCZ();   //   检修人员操作
//        if (cb_left.isEmpty()) { // 监护状态下进行的操作
//
//        } else {
//            mCb_exe_left.setChecked(true);
//        }
//        if (cb_center.isEmpty()) {//   单人操作
//
//        } else {
//            mCb_exe_center.setChecked(true);
//        }
//        if (cb_right.isEmpty()) {   //   检修人员操作
//
//        } else {
//            mCb_exe_right.setChecked(true);
//        }
//        mTv_exe_bzd_address.setText(recordsBean.getGZDDMC());
//        mTv_exe_pd.setText(recordsBean.getGZDDMS());
//        mTv_exe_dl_num.setText(recordsBean.getDLBH());
//        mTv_exe_ticket_num.setText(recordsBean.getPH());
//        mTv_exe_flr_name.setText(recordsBean.getFLRMC());
//        mTv_exe_jlr_name.setText(recordsBean.getSLRMC());
//        if (recordsBean.getFLSJ() == null || "".equals(recordsBean.getFLSJ()) || recordsBean.getFLSJ().length() < 18) {
//            mTv_exe_fl_time.setText("");
//        } else {
//            String flTime = recordsBean.getFLSJ().substring(0, recordsBean.getFLSJ().length() - 3);
//            mTv_exe_fl_time.setText(flTime);
//        }
//
//        if (recordsBean.getCZKGSJ() == null || "".equals(recordsBean.getCZKGSJ()) || recordsBean.getCZKGSJ().length() < 18) {
//            mTv_exe_czks_time.setText("");
//        } else {
//            String czksTime = recordsBean.getCZKGSJ().substring(0, recordsBean.getCZKGSJ().length() - 3);
//            mTv_exe_czks_time.setText(czksTime);
//        }
//        if (recordsBean.getCZJSSJ() == null || "".equals(recordsBean.getCZJSSJ()) || recordsBean.getCZJSSJ().length() < 18) {
//            mTv_exe_czjs_time.setText("");
//        } else {
//            String czjsTime = recordsBean.getCZJSSJ().substring(0, recordsBean.getCZJSSJ().length() - 3);
//            mTv_exe_czjs_time.setText(czjsTime);
//        }
//        mTv_exe_czrw.setText(recordsBean.getCZRW());
//        mTv_exe_bz.setText(recordsBean.getBZ());
//        mTv_exe_qm_tpr.setText("");
//        mTv_exe_qm_spr.setText("");
//        mTv_exe_qm_fzr.setText(recordsBean.getZBFZRMC());
//        mTv_exe_qm_czr.setText(recordsBean.getCZRMC());
//        mTv_exe_qm_jhr.setText(recordsBean.getJHRMC());
//        mTv_exe_qm_tpsj.setText("");
//        mExecutionActivityAdapter = new ExecutionActivityAdapter(ExecutionActivity.this, mCzbzArray, lv_exe_czxm, objId);//   lv_exe_czxm
////        mTv_exe_qm_tpr.setText("");
////        mTv_exe_qm_spr.setText("");
////        mTv_exe_qm_fzr.setText(recordsBean.getZBFZRMC());
////        mTv_exe_qm_czr.setText(recordsBean.getCZRMC());
////        mTv_exe_qm_jhr.setText(recordsBean.getJHRMC());
////        mTv_exe_qm_tpsj.setText("");
//        lv_exe_czxm.setAdapter(mExecutionActivityAdapter);

    }

    /*保存接口
    * */
    private void saveCZPInfo() {
////        Drawable.ConstantState yesSta = getResources().getDrawable(R.mipmap.caozuo_yes).getConstantState();
////        Drawable.ConstantState noSta = getResources().getDrawable(R.mipmap.caozuo_no).getConstantState();
////        Drawable.ConstantState morenSta = getResources().getDrawable(R.mipmap.caozuo_moren).getConstantState();
//        String str = "";
//        String time = "";
//        String statues;
//        for (int i = 0; i < lv_exe_czxm.getChildCount(); i++) {
//            //得到对应的item的view
//            View view = lv_exe_czxm.getChildAt(i);
//            //view.findViewById(R.id.tv_exe_order_one);
//            //从view中取得holder
//            ExecutionActivityAdapter.ViewHolder holder = (ExecutionActivityAdapter.ViewHolder) view.getTag();
//            String order = holder.tv_exe_order_one.getText().toString().trim();
//            String stemp = holder.tv_exe_czxm_one.getText().toString().trim();
//            //    String time = holder.tv_exe_zxsj_one.getText().toString().trim();
//
//            if (mCzbzArray.length <= i) {
//
//            } else {
//                String orderS = mCzbzArray[i];
//                String[] orderArray = StringUtil.splitString("\\|", orderS); //   切割字符串
////                if (orderArray.length >= 3) {
////                    time = orderArray[orderArray.length - 1];
////                }
//                for (int j = 0; j < orderArray.length; j++) {
//                    if (orderArray[j].length() == 19 && orderArray[j].contains(":")) {
//                        time = orderArray[j];
//                    } else {
//
//                    }
//                }
//            }
//
//            if ("isYes".equals(holder.tv_exe_yes.getTag())) {
//                statues = "√";
//            } else if ("isNO".equals(holder.tv_exe_no.getTag())) {
//                statues = "×";
//            } else {
//                statues = "";
//            }
//            //   "1|21221|√|2017-07-06 15:04:04@@2|ggg|√|2017-07-25 18:50:09"
//            str += order + "|" + stemp + "|" + statues + "|" + time.trim() + "@@";
//        }
//        String strSub = str.substring(0, str.length() - 2);
//        Log.e("lala", "excutiongactivity中strSub=============" + strSub);
//        String flrName = mTv_exe_flr_name.getText().toString().trim();
//        String czStarTime = mTv_exe_czks_time.getText().toString().trim() + ":00";
//        String czFinshTime = mTv_exe_czjs_time.getText().toString().trim() + ":00";
//        String jh, dr, jx;
//        if (jianhu) {
//            jh = "√";
//        } else {
//            jh = "";
//        }
//        if (danren) {
//            dr = "√";
//        } else {
//            dr = "";
//        }
//        if (jianxiu) {
//            jx = "√";
//        } else {
//            jx = "";
//        }
//        String beizhu = mTv_exe_bz.getText().toString().trim();
//        String qm_czr = mTv_exe_qm_czr.getText().toString().toString();
//        String qm_jhr = mTv_exe_qm_jhr.getText().toString().toString();
//        String qm_fzr = mTv_exe_qm_fzr.getText().toString().toString();
//
//        if (mRYMC == null || "".equals(mRYMC) || mUserId == null || "".equals(mUserId) || objId == null || mbId == null) {
//            Toast.makeText(ExecutionActivity.this, "登陆用户名为空，请重新登陆mip", Toast.LENGTH_SHORT).show();
//        } else {
//            if (danren == false && jianhu == false && jianxiu == false) {
//                Toast.makeText(ExecutionActivity.this, "必须选择一种操作模式才可保存", Toast.LENGTH_SHORT).show();
//            } else {
//                StringBuilder sb = new StringBuilder();
//                //拼接请求体
//                sb.append("<config>").append("\n")
//                        .append("<list>").append("\n")
//                        .append("<params>").append("\n")
//                        .append("<USER_NAME>>")  //人员名称
//                        .append(mRYMC)
//                        .append("</USER_NAME>>").append("\n")
//                        .append("<USER_ID>")  //用户id
//                        .append(mUserId)
//                        .append("</USER_ID>").append("\n")
//                        .append("<PID>")  //票id
//                        .append(objId)
//                        .append("</PID>").append("\n")
//                        .append("<MKLX>")
//                        .append("2")
//                        .append("</MKLX>").append("\n")
//                        .append("<MBID>")
//                        .append(mbId)
//                        .append("</MBID>").append("\n")
//                        .append("</params>").append("\n")
//                        .append("</list>").append("\n")
//
//                        .append("<patterns>").append("\n")
//                        .append("<pattern>").append("\n")
//
//                        .append("<item key=\"发令人名称\">").append("\n")
//                        .append(flrName)
//                        .append("</item>").append("\n")
//                        .append("<item key=\"操作开工时间\">").append("\n")
//                        .append(czStarTime)
//                        .append("</item>").append("\n")
//
//                        .append("<item key=\"操作结束时间\">").append("\n")
//                        .append(czFinshTime)
//                        .append("</item>").append("\n")
//
//                        .append("<item key=\"监护下操作\">").append("\n")
//                        .append(jh)
//                        .append("</item>").append("\n")
//
//                        .append("<item key=\"单人操作\">").append("\n")
//                        .append(dr)
//                        .append("</item>").append("\n")
//
//                        .append("<item key=\"监修人员操作\">").append("\n")
//                        .append(jx)
//                        .append("</item>").append("\n")
//
//                        .append("<item key=\"操作步骤\">").append("\n")
//                        .append(strSub)   // ”监修人员操作”  "1|21221|√|2017-07-06 15:04:04@@2|ggg|√|2017-07-25 18:50:09"
//                        .append("</item>").append("\n")
//
//                        .append("<item key=\"备注\">").append("\n")
//                        .append(beizhu)
//                        .append("</item>").append("\n")
//
//                        .append("<item key=\"操作人名称\">").append("\n")
//                        .append(qm_czr)
//                        .append("</item>").append("\n")
//
//                        .append("<item key=\"监护人名称\">").append("\n")
//                        .append(qm_jhr)
//                        .append("</item>").append("\n")
//
//                        .append("<item key=\"值班负责人名称\">").append("\n")
//                        .append(qm_fzr)
//                        .append("</item>").append("\n")
//
//                        .append("</pattern>").append("\n")
//                        .append("</patterns>").append("\n")
//                        .append("</config>");
//                String serviceName = "sxlp1"; //   服务名
//                String interfaceName = "saveCZPInfo";//   接口名
//                Object[] params = new Object[]{sb};
//                String data = DataReadUtil.getDataFromDb(serviceName, interfaceName, params);
//                Log.e("lala", "保存接口返回数据data========" + data);
//                if (data == null && "".equals(data)) {
//                    Toast.makeText(ExecutionActivity.this, "请检查网络", Toast.LENGTH_SHORT).show();
//                } else {
//                    JSONObject datas;
//                    try {
//                        datas = new JSONObject(data);
//                        JSONArray records = datas.getJSONArray("records");
//                        if (records.length() == 0) {
//                            Toast.makeText(ExecutionActivity.this, "数据为空", Toast.LENGTH_SHORT).show();
//                        } else {
//                            Log.e("lala", "records=======" + records.toString());
//                            JSONObject record = records.getJSONObject(0);
//                            //工作票主键
//                            String flag = record.getString("FLAG");
//                            if ("true".equals(flag)) {
//                                Toast.makeText(ExecutionActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
//                                finish();
//                            } else {
//                                Toast.makeText(ExecutionActivity.this, "保存失败，请检查网络和上传参数", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//
//                    }
//                }
//            }
//
//        }


    }

    private void initCustomTimePicker() {
//
//        /**
//         * @description
//         *
//         * 注意事项：
//         * 1.自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针.
//         * 具体可参考demo 里面的两个自定义layout布局。
//         * 2.因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
//         * setRangDate方法控制起始终止时间(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
//         */
//        Calendar selectedDate = Calendar.getInstance();//系统当前时间
//        Calendar startDate = Calendar.getInstance();
//        startDate.set(2014, 1, 23);
//        Calendar endDate = Calendar.getInstance();
//        endDate.set(2027, 2, 28);
//        //时间选择器 ，自定义布局
//        pvCustomTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
//            @Override
//            public void onTimeSelect(Date date, View v) {//选中事件回调
//                if (mTimeStyle == 1) {   //  1：发令时间 ；
//                    mTv_exe_fl_time.setText(getTime(date));
//                } else if (mTimeStyle == 2) { //   2：操作开始时间 ；
//                    mTv_exe_czks_time.setText(getTime(date));
//                    mDate1 = date;
//                } else if (mTimeStyle == 3) {   // 3： 操作结束时间；
//                   String czksT =  mTv_exe_czks_time.getText().toString().trim();
//                    //把string转化为date
//                    DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd HH:mm");
//                    Date d = null;
//                    try {
//                        d = fmt.parse(czksT);
//                    } catch (ParseException e) {
//                        e.printStackTrace();
//                    }
//                    if (czksT == null || "".equals(czksT)) {
//                        Toast.makeText(ExecutionActivity.this, "请先选择操作开始时间！", Toast.LENGTH_LONG).show();
//                    } else {
////                    if (mDate1 == null || "".equals(mDate1)) {
////                        Toast.makeText(ExecutionActivity.this, "请先选择操作开始时间！", Toast.LENGTH_LONG).show();
////                    }else {
//                        if (date.after(d)) {
//                            mTv_exe_czjs_time.setText(getTime(date));
//                        } else {
//                            Toast.makeText(ExecutionActivity.this, "操作结束时间必须晚于操作开始时间！", Toast.LENGTH_LONG).show();
//                        }
//                    }
//
////                    }
//               }
//
//            }
//        })
//                /*.setType(TimePickerView.Type.ALL)//default is all
//                .setCancelText("Cancel")
//                .setSubmitText("Sure")
//                .setContentSize(18)
//                .setTitleSize(20)
//                .setTitleText("Title")
//                .setTitleColor(Color.BLACK)
//               /*.setDividerColor(Color.WHITE)//设置分割线的颜色
//                .setTextColorCenter(Color.LTGRAY)//设置选中项的颜色
//                .setLineSpacingMultiplier(1.6f)//设置两横线之间的间隔倍数
//                .setTitleBgColor(Color.DKGRAY)//标题背景颜色 Night mode
//                .setBgColor(Color.BLACK)//滚轮背景颜色 Night mode
//                .setSubmitColor(Color.WHITE)
//                .setCancelColor(Color.WHITE)*/
//               /*.gravity(Gravity.RIGHT)// default is center*/
//                .setDate(selectedDate)
//                .setRangDate(startDate, endDate)
//                .setLayoutRes(R.layout.pickerview_custom_time, new CustomListener() {
//
//                    @Override
//                    public void customLayout(View v) {
//                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
//                        ImageView ivCancel = (ImageView) v.findViewById(R.id.iv_cancel);
//                        tvSubmit.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                pvCustomTime.returnData();
//                                pvCustomTime.dismiss();
//                            }
//                        });
//                        ivCancel.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                pvCustomTime.dismiss();
//                            }
//                        });
//                    }
//                })
//                .setContentSize(18)
//                .setType(new boolean[]{true, true, true, true, true, false})
//                // .setType(new boolean[]{false, false, false, true, true, true})
//                .setLabel("年", "月", "日", "时", "分", "秒")
//                .setLineSpacingMultiplier(1.2f)
//                .setTextXOffset(0, 0, 0, 0, 0, 0) // (0, 0, 0, 40, 0, -40)  设置滚轮样式
//                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
//                .setDividerColor(0xFF24AD9D)
//                .build();

    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm"); ///    "yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }
}
