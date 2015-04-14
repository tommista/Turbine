package tommista.com.turbine2.ui.Timeline;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.ListView;

import javax.inject.Inject;

import tommista.com.turbine2.R;
import tommista.com.turbine2.TurbineApp;
import tommista.com.turbine2.Tweets;
import tommista.com.turbine2.adapters.TweetAdapter;
import tommista.com.turbine2.net.TwitterAPI;

public class TimelineView extends LinearLayout{

    private Context context;
    private ListView listView;

    @Inject TwitterAPI twitterAPI;
    @Inject Tweets tweets;
    @Inject TweetAdapter tweetAdapter;

    public TimelineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        TurbineApp.get(context).inject(this);
    }

    @Override
    protected void onFinishInflate(){
        super.onFinishInflate();

        listView = (ListView) findViewById(R.id.main_list_view);

    }

}
