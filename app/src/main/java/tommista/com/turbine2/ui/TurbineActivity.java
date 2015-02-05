package tommista.com.turbine2.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

import javax.inject.Inject;

import tommista.com.turbine2.R;
import tommista.com.turbine2.TurbineApp;


public class TurbineActivity extends ActionBarActivity {

    @Inject
    AppContainer appContainer;

    //private MortarActivityScope activityScope;
    private ViewGroup container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TurbineApp app = TurbineApp.get(this);
        app.inject(this);

        container = appContainer.get(this);

        //MortarScope parentScope = ((BirdcageApp) getApplication()).getRootScope();
        //activityScope = Mortar.requireActivityScope(parentScope, new Main());
        //Mortar.inject(this, this);


        getLayoutInflater().inflate(R.layout.timeline_view, container);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
