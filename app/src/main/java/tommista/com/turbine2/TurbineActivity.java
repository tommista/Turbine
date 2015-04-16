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


    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_turbine, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            setContentView(R.layout.settings_view);
            return true;
        }
        else if(id == R.id.action_refresh){

        }

        return super.onOptionsItemSelected(item);
    }*/

    @Override
    public void onBackPressed(){
        Timber.i("back button pressed");
        setContentView(R.layout.timeline_view);
    }
}
