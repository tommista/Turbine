package tommista.com.turbine2;

import android.app.Activity;
import android.os.Bundle;

import timber.log.Timber;

public class TurbineActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TurbineApp app = TurbineApp.get(this);
        app.inject(this);

        setContentView(R.layout.timeline_view);
    }

    @Override
    public void onBackPressed(){
        Timber.i("back button pressed");
        setContentView(R.layout.timeline_view);
    }
}