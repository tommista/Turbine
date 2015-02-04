package tommista.com.turbine2.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.ListView;

/**
 * Created by tbrown on 2/4/15.
 */
public class TimelineView extends LinearLayout{

    public ListView listView;

    public TimelineView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate(){
        super.onFinishInflate();
    }

}
