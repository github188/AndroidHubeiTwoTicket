package tickets.nari.com.hubeitickets.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.bigkoo.pickerview.listener.CustomListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import tickets.nari.com.hubeitickets.R;
import tickets.nari.com.hubeitickets.adapter.CaoZuoXM_ListAdapter;
import tickets.nari.com.hubeitickets.customview.CustomSignatureDialog;

import static com.nari.tickets.baselibrary.util.CommonUtil.setListViewHeightBasedOnChildren;


/**
 * 执行中界面
 */

public class TicketDetail_Activity extends FragmentActivity implements View.OnClickListener {

    private ImageView back;//返回
    private TextView mTv_exe_czrw;    //   操作任务

    private TextView mTv_exe_bz;   //   备注
    private TextView mTv_exe_qm_fzr; //   负责人
    private TextView mTv_exe_qm_czr;  //   操作人
    private TextView mTv_exe_qm_jhr; //   监护人

    private TextView mTv_exe_look_all; // 查看全部
    private ListView lv_exe_czxm;
    private CaoZuoXM_ListAdapter caoZuoXM_listAdapter;
    public RelativeLayout re_exe_title;
    boolean jianhu, danren, jianxiu;// RadioButton选择状态
    private String objId;
    private String mbId;
    private TimePickerView pvCustomTime;
    private int mTimeStyle;   //   用于区分是那个时间按钮  2：操作开始时间 ； 3： 操作结束时间；
    private Date mDate1; //   操作开始时间
    int ticketType = 0;//用于区分是哪个状态的操作票的详情0：待执行 1:执行中 2:归档票
    private RadioGroup czRg;
    private RadioButton jkRb, drRb, jxRb;
    private TextView kssj, jssj;
    private TextView czr_xm, jhr_xm, ywfzr_xm;
    private ImageView czr_right, jhr_right, ywfzr_right;
    private TextView top_name, czdw, bh;

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
        ticketType = getIntent().getIntExtra("ticketType", 2);//默认是已归档状态，可重新修改默认状态
        if (ticketType==2){//这个布局只是为了解决底部去掉按钮之后的留白，时间紧，不想看怎么实现，就用了俩布局
            setContentView(R.layout.execution_ticket_oth);
        }else {
            setContentView(R.layout.execution_ticket);
        }
        init();
    }


    /**
     * 初始化控件
     */
    private void init() {

        ScrollView ticket_content  = findViewById(R.id.ticket_content);
        re_exe_title = findViewById(R.id.re_exe_title);
        TextView ticket_title_tv = findViewById(R.id.ticket_title_tv);
        back = (ImageView) findViewById(R.id.image_execution_activity_back);
        mTv_exe_czrw = findViewById(R.id.tv_exe_czrw);
        mTv_exe_czrw.setMovementMethod(ScrollingMovementMethod.getInstance());
        mTv_exe_czrw.setText("desalhngoairghowpighwporgihwporghiwporghipworhigpworhegpowrighpowghiowghosweghi tvnupvworut vnwotr          rqweitpoqwqwrghipofgsgnbv;lsfnbgslfkgns;lghfpowrgihwporihgporwghfposghfposghfpsohgpwghpowhfigpo;shf");
        mTv_exe_bz = findViewById(R.id.tv_exe_bz);
        lv_exe_czxm = findViewById(R.id.lv_exe_czxm);

        //此处为操作项目列表，从后台取值处理后传入adapter展示
        caoZuoXM_listAdapter = new CaoZuoXM_ListAdapter(this, null);
        lv_exe_czxm.setAdapter(caoZuoXM_listAdapter);
        setListViewHeightBasedOnChildren(lv_exe_czxm);//动态设置ListView的高度,解决ScrollView与ListView冲突

        mTv_exe_look_all = findViewById(R.id.tv_exe_look_all);
        czRg = findViewById(R.id.cz_rg);
        jkRb = findViewById(R.id.cb_jk);
        drRb = findViewById(R.id.cb_dr);
        jxRb = findViewById(R.id.cb_jx);
        czr_xm = findViewById(R.id.czr_xm);
        jhr_xm = findViewById(R.id.jhr_xm);
        ywfzr_xm = findViewById(R.id.ywfzr_xm);
        czr_right = findViewById(R.id.czr_right);
        jhr_right = findViewById(R.id.jhr_right);
        ywfzr_right = findViewById(R.id.ywfzr_right);
        kssj = findViewById(R.id.check_cz_start_time);
        jssj = findViewById(R.id.check_cz_finsh_time);
        top_name = findViewById(R.id.tv_check_top_name);
        bh = findViewById(R.id.check_bh);//编号
        czdw = findViewById(R.id.check_czdw);//操作单位

        czRg.check(R.id.cb_jk);//设置哪一个被选中
        jianhu = true;//被选中后对应的标记需要被记录


        if (ticketType == 0) {//待执行
            ticket_title_tv.setText("待执行");
            LinearLayout button_process = findViewById(R.id.ticket_button_process);
            button_process.setVisibility(View.VISIBLE);
            Button ticket_zuofei = findViewById(R.id.ticket_zuofei);
            ticket_zuofei.setOnClickListener(this);
            Button ticket_zhixing = findViewById(R.id.ticket_zhixing);
            ticket_zhixing.setOnClickListener(this);
        } else if (ticketType == 1) {//执行中
             ticket_title_tv.setText("执行中");
            LinearLayout button_execution = findViewById(R.id.ticket_button_execution);
            button_execution.setVisibility(View.VISIBLE);
            Button ticket_weizhixing = findViewById(R.id.ticket_weizhixing);
            ticket_weizhixing.setOnClickListener(this);
            Button ticket_zhongjie = findViewById(R.id.ticket_zhongjie);
            ticket_zhongjie.setOnClickListener(this);
        } else {//归档票
            ticket_title_tv.setText("归档票");
           RelativeLayout ticket_bottom_button= findViewById(R.id.ticket_bottom_button);
            ticket_bottom_button.setVisibility(View.GONE);
            czRg.setEnabled(false);//不能选择
            jkRb.setEnabled(false);//不能选择
            drRb.setEnabled(false);//不能选择
            jxRb.setEnabled(false);//不能选择
        }


        objId = getIntent().getStringExtra("obj_id");
        mbId = getIntent().getStringExtra("mbId");
        initListener();
    }

    private void initListener() {
        back.setOnClickListener(this);
        mTv_exe_look_all.setOnClickListener(this);

        if (ticketType == 0 || ticketType == 1) {

            kssj.setOnClickListener(this);
            jssj.setOnClickListener(this);

            czr_xm.setOnClickListener(this);
            czr_right.setOnClickListener(this);
            jhr_right.setOnClickListener(this);
            jhr_xm.setOnClickListener(this);
            ywfzr_right.setOnClickListener(this);
            jhr_xm.setOnClickListener(this);
            czRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    switch (i) {
                        case R.id.cb_jk:
                            jianhu = true;
                            danren = false;
                            jianxiu = false;
                            jhr_xm.setText("");
                            break;
                        case R.id.cb_dr:
                            jianhu = false;
                            danren = true;
                            jianxiu = false;
                            jhr_xm.setText("(空)");//当点击单人操作时选项时，加护人签名处默认显示“（空）”，字段
                            break;
                        case R.id.cb_jx:
                            jianhu = false;
                            danren = false;
                            jianxiu = true;
                            jhr_xm.setText("");
                            break;
                    }
                }
            });
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_execution_activity_back://返回按钮
                finish();
                break;
            case R.id.tv_exe_look_all://查看全部
                Intent intent = new Intent(this, OperationStepsActivity.class);
                startActivity(intent);
                break;
            case R.id.check_cz_start_time://开始时间选择
                mTimeStyle = 2;
                if (pvCustomTime == null) {
                    initCustomTimePicker();
                }
                pvCustomTime.show();
                break;

            case R.id.check_cz_finsh_time://结束时间选择
                mTimeStyle = 3;
                if (pvCustomTime == null) {
                    initCustomTimePicker();
                }
                pvCustomTime.show();
                break;
            case R.id.czr_xm://操作人选择
                if (danren == false && jianhu == false && jianxiu == false) {
                    Toast.makeText(getApplicationContext(), "必须选择一种操作模式", Toast.LENGTH_SHORT).show();
                } else {
                    CustomSignatureDialog dialog3 = new CustomSignatureDialog(TicketDetail_Activity.this, objId, mbId, new CustomSignatureDialog.MyListener() {
                        @Override
                        public void getSpinnerText(String userName) {
                            String fzr = userName;
                            czr_xm.setText(fzr);
                        }
                    });
                    dialog3.setTitle("操作人签名");
                }
                break;
            case R.id.jhr_xm://监护人选择
                if (danren == false && jianhu == false && jianxiu == false) {
                    Toast.makeText(getApplicationContext(), "必须选择一种操作模式", Toast.LENGTH_SHORT).show();
                } else {
                    CustomSignatureDialog dialog3 = new CustomSignatureDialog(TicketDetail_Activity.this, objId, mbId, new CustomSignatureDialog.MyListener() {
                        @Override
                        public void getSpinnerText(String userName) {
                            String fzr = userName;
                            jhr_xm.setText(fzr);
                        }
                    });
                    dialog3.setTitle("监护人签名");
                }
                break;
            case R.id.ywfzr_xm:////运维负责人选择
                if (danren == false && jianhu == false && jianxiu == false) {
                    Toast.makeText(getApplicationContext(), "必须选择一种操作模式", Toast.LENGTH_SHORT).show();
                } else {
                    CustomSignatureDialog dialog3 = new CustomSignatureDialog(TicketDetail_Activity.this, objId, mbId, new CustomSignatureDialog.MyListener() {
                        @Override
                        public void getSpinnerText(String userName) {
                            String fzr = userName;
                            ywfzr_xm.setText(fzr);
                        }
                    });
                    dialog3.setTitle("运维负责人签名");
                }
                break;

            case R.id.ticket_zuofei://作废按钮

                break;
            case R.id.ticket_zhixing://执行按钮

                break;
            case R.id.ticket_weizhixing://未执行按钮

                break;
            case R.id.ticket_zhongjie://终结按钮

                break;
            default:
                break;
        }
    }


    private void initCustomTimePicker() {

        /**
         * @description
         *
         * 注意事项：
         * 1.自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针.
         * 具体可参考demo 里面的两个自定义layout布局。
         * 2.因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
         * setRangDate方法控制起始终止时间(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
         */
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(2014, 1, 23);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2027, 2, 28);
        //时间选择器 ，自定义布局
        pvCustomTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                if (mTimeStyle == 1) {   //  1：发令时间 ；
//                    mTv_exe_fl_time.setText(getTime(date));
                } else if (mTimeStyle == 2) { //   2：操作开始时间 ；
                    kssj.setText(getTime(date));
                    mDate1 = date;
                } else if (mTimeStyle == 3) {   // 3： 操作结束时间；
                    String czksT = kssj.getText().toString().trim();
                    if (TextUtils.isEmpty(czksT)) {
                        Toast.makeText(TicketDetail_Activity.this, "请先选择操作开始时间！", Toast.LENGTH_LONG).show();
                    } else {
                        if (mDate1 != null && date.after(mDate1)) {
                            jssj.setText(getTime(date));
                        } else {
                            Toast.makeText(TicketDetail_Activity.this, "操作结束时间必须晚于操作开始时间！", Toast.LENGTH_LONG).show();
                        }
                    }
                }

            }
        })
                /*.setType(TimePickerView.Type.ALL)//default is all
                .setCancelText("Cancel")
                .setSubmitText("Sure")
                .setContentSize(18)
                .setTitleSize(20)
                .setTitleText("Title")
                .setTitleColor(Color.BLACK)
               /*.setDividerColor(Color.WHITE)//设置分割线的颜色
                .setTextColorCenter(Color.LTGRAY)//设置选中项的颜色
                .setLineSpacingMultiplier(1.6f)//设置两横线之间的间隔倍数
                .setTitleBgColor(Color.DKGRAY)//标题背景颜色 Night mode
                .setBgColor(Color.BLACK)//滚轮背景颜色 Night mode
                .setSubmitColor(Color.WHITE)
                .setCancelColor(Color.WHITE)*/
               /*.gravity(Gravity.RIGHT)// default is center*/
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setLayoutRes(R.layout.pickerview_custom_time, new CustomListener() {

                    @Override
                    public void customLayout(View v) {
                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                        ImageView ivCancel = (ImageView) v.findViewById(R.id.iv_cancel);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomTime.returnData();
                                pvCustomTime.dismiss();
                            }
                        });
                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomTime.dismiss();
                            }
                        });
                    }
                })
                .setContentSize(18)
                .setType(new boolean[]{true, true, true, true, true, false})
                // .setType(new boolean[]{false, false, false, true, true, true})
                .setLabel("年", "月", "日", "时", "分", "秒")
                .setLineSpacingMultiplier(1.2f)
                .setTextXOffset(0, 0, 0, 0, 0, 0) // (0, 0, 0, 40, 0, -40)  设置滚轮样式
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(0xFF24AD9D)
                .build();

    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm"); ///    "yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }
}
