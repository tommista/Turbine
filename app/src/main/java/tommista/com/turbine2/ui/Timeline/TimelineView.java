package tommista.com.turbine2.ui.Timeline;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

import javax.inject.Inject;

import tommista.com.turbine2.TurbineApp;
import tommista.com.turbine2.models.Handle;
import tommista.com.turbine2.net.TwitterAPI;

import static tommista.com.turbine2.TurbineModule.HandleList;

public class TimelineView extends LinearLayout{

    public ListView listView;

    @Inject
    TwitterAPI twitterAPI;

    @Inject @HandleList
    ArrayList<Handle> handleList;

    public TimelineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TurbineApp.get(context).inject(this);
    }

    @Override
    protected void onFinishInflate(){
        super.onFinishInflate();
    }

}
