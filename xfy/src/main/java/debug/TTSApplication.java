package debug;

import com.ovo.common.app.BaseApplication;
import com.ovo.xfy.ise.ISEUtlis;
import com.ovo.xfy.tts.xfyun.XFYTTS;

public class TTSApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        XFYTTS.getIntance().initXFY(this);
        ISEUtlis.getIntance().initISE(this);
    }
}
