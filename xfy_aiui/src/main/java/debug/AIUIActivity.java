package debug;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ovo.common.base.BaseActivity;
import com.ovo.common.impl.XFYAIUIListener;
import com.ovo.xfy_aiui.AIUIUtils;
import com.ovo.xfy_aiui.R;

import butterknife.BindView;

public class AIUIActivity extends BaseActivity implements View.OnClickListener, XFYAIUIListener {

    @BindView(R.id.nlp_text)
    EditText nlpText;
    @BindView(R.id.nlp_start)
    Button nlpStart;

    @Override
    public int layout() {
        return R.layout.aiui;
    }

    @Override
    public void initView() {
        nlpStart.setOnClickListener(this);
        AIUIUtils.getInstance().setXfyaiuiListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 开始语音理解
            case R.id.nlp_start:
                AIUIUtils.getInstance().startVoiceNlp();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AIUIUtils.getInstance().closeAIUI();
    }

    @Override
    public void AIUIResult(String result) {
        nlpText.setText(result);
        nlpText.setText("\n");
    }

    @Override
    public void AIUIRecode(String recode) {

    }
}
