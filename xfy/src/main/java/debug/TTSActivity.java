package debug;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ovo.common.base.BaseActivity;
import com.ovo.xfy.R;
import com.ovo.xfy.tts.xfyun.XFYTTS;

import butterknife.BindView;

public class TTSActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.et_input)
    EditText etInput;
    @BindView(R.id.bt_tts)
    Button btTts;

    @Override
    public int layout() {
        return R.layout.tts;
    }

    @Override
    public void initView() {
        btTts.setOnClickListener(this);
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
                XFYTTS.getIntance().startPlayXFY(etInput.getText().toString());
                break;
        }
    }
}
