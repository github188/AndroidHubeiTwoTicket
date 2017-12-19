package tickets.nari.com.hubeitickets.activity;

import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tickets.nari.com.hubeitickets.R;
import tickets.nari.com.hubeitickets.adapter.StepsListViewAdapter;

/**
 * Created by xieshibao on 2017/11/23.、
 * 操作项目界面
 */

public class OperationStepsActivity extends FragmentActivity implements View.OnClickListener{

    private ImageView back;
    private ListView operation_xiangmuList;
    private StepsListViewAdapter stepsListViewAdapter;
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
        setContentView(R.layout.operation_steps);
        init();
        final List<Map<String, String>> list = getData();
        stepsListViewAdapter = new StepsListViewAdapter(getApplicationContext(), list);
        operation_xiangmuList.setAdapter(stepsListViewAdapter);
//        downListView.setAdapter(stepsListViewAdapter);

    }

    private void init() {
        operation_xiangmuList = (ListView) findViewById(R.id.operation_xiangmu);
         back = (ImageView) findViewById(R.id.image_operationSteps_activity_back);
       back.setOnClickListener(this);

    }

    public List<Map<String, String>> getData() {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        for (int i = 0; i < 10; i++) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("time", "2017/09/26 09:30");
            map.put("content", "这里输入步骤内容这里输入步骤内容这里输入步骤内容这里输入步骤内容这" +
                    "里输入步骤内容这里输入步骤内容这里输入步骤内容这里输入步骤内容这里输入步骤内容这里输入步骤内容这里输入步骤内容这里输入步骤内容这");
            list.add(map);
        }
        return list;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.image_operationSteps_activity_back:
                finish();
                break;

        }
    }
}

