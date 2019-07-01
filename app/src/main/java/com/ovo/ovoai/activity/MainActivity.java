package com.ovo.ovoai.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.ovo.common.base.BaseActivity;
import com.ovo.common.utils.pingyin.PinYinUtils;
import com.ovo.common.utils.pingyin.PinyinUtil;
import com.ovo.ovoai.R;
import com.ovo.ovoai.app.MyApplication;
import com.ovo.ovoai.utils.LogUtils;
import com.ovo.ovoai.utils.StringUtils;
import com.ovo.ovoai.utils.ToastUtils;
import com.ovo.unity.utils.U3dUtils;

import butterknife.BindView;

@Route(path = "/main/activity")
public class MainActivity extends BaseActivity {

    @BindView(R.id.et_text)
    EditText etText;
    @BindView(R.id.bt_go)
    Button btGo;

    @Override
    public int layout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        etText.setSelection(etText.getText().toString().length());
        btGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = etText.getText().toString();

                LogUtils.log(text);
//                LogUtils.log("1");
                char[]texts = text.toCharArray();
//                LogUtils.log("2");
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < texts.length; i++) {
                    String py = PinyinUtil.polishPinyin(PinYinUtils.getPinYin(texts[i]));
                    sb.append(py);
                }
//                LogUtils.log("3");
                LogUtils.log(sb.toString());

                if (!StringUtils.isEmpty(text)) {
                    U3dUtils.getInstance().setData(text);
                    U3dUtils.getInstance().setPYData(sb.toString());
                    ARouter.getInstance().build("/unity/activity").navigation();
//                    Intent intent = new Intent(MyApplication.getInstance().getContext(),
//                            UnityPlayerActivity.class);
//                    startActivity(intent);
                } else {
                    ToastUtils.show(MyApplication.getInstance().getContext(), "请输入驱动词");
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
