package debug;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ovo.common.app.BaseApplication;
import com.ovo.common.base.BaseActivity;
import com.ovo.xfy.R;
import com.ovo.xfy.ise.ISEUtlis;

import butterknife.BindView;

public class ISEActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.bt_tts)
    Button btTts;
    @BindView(R.id.tv_ise)
    TextView tvIse;
    @BindView(R.id.tv_result)
    TextView tvResult;
    @BindView(R.id.bt_start)
    Button btStart;
    @BindView(R.id.bt_end)
    Button btEnd;

    @Override
    public int layout() {
        return R.layout.ise;
    }

    @Override
    public void initView() {
        btTts.setOnClickListener(this);
        btStart.setOnClickListener(this);
        btEnd.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_tts:
                Intent intent = new Intent
                        (BaseApplication.getInstance().getContext(), TTSActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_start:
                ISEUtlis.getIntance().startEvaluating(tvIse.getText().toString());
                break;
            case R.id.bt_end:
                ISEUtlis.getIntance().stopEvaluating();
                break;
        }
    }
}
