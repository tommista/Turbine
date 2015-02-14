package tommista.com.turbine2.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import timber.log.Timber;
import tommista.com.turbine2.R;
import tommista.com.turbine2.TurbineApp;


public class TurbineActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TurbineApp app = TurbineApp.get(this);
        app.inject(this);

        setContentView(R.layout.timeline_view);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_turbine, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            setContentView(R.layout.settings_view);
            return true;
        }
        else if(id == R.id.action_refresh){

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed(){
        Timber.i("back button pressed");
        setContentView(R.layout.timeline_view);
    }
}
