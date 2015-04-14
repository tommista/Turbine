package tommista.com.turbine2.ui.Timeline;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.PriorityQueue;

import javax.inject.Inject;

import tommista.com.turbine2.R;
import tommista.com.turbine2.TurbineApp;
import tommista.com.turbine2.adapters.TweetAdapter;
import tommista.com.turbine2.models.Handle;
import tommista.com.turbine2.models.Tweet;
import tommista.com.turbine2.net.TwitterAPI;

import static tommista.com.turbine2.TurbineModule.HandleList;
import static tommista.com.turbine2.TurbineModule.TweetList;

public class TimelineView extends LinearLayout{

    private Context context;
    private ListView listView;
    private TweetAdapter tweetAdapter;

    @Inject TwitterAPI twitterAPI;

    @Inject @HandleList ArrayList<Handle> handleList;
    @Inject @TweetList PriorityQueue<Tweet> tweetList;

    public TimelineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        TurbineApp.get(context).inject(this);
    }

    @Override
    protected void onFinishInflate(){
        super.onFinishInflate();

        listView = (ListView) findViewById(R.id.main_list_view);

        tweetAdapter = new TweetAdapter(context, new ArrayList<Tweet>(tweetList));

        listView.setAdapter(tweetAdapter);

    }

}
