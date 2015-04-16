package tommista.com.turbine2.ui.Timeline;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.ListView;

import javax.inject.Inject;

import tommista.com.turbine2.DataFuser;
import tommista.com.turbine2.R;
import tommista.com.turbine2.TurbineApp;
import tommista.com.turbine2.Tweets;
import tommista.com.turbine2.adapters.TweetAdapter;

public class TimelineView extends LinearLayout{

    private Context context;
    private ListView listView;

    private BroadcastReceiver newTweetReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            tweetAdapter.notifyDataSetChanged();
        }
    };

    @Inject Tweets tweets;
    @Inject TweetAdapter tweetAdapter;
    @Inject DataFuser dataFuser;

    public TimelineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        LocalBroadcastManager.getInstance(context).registerReceiver(newTweetReceiver, new IntentFilter(context.getResources().getString(R.string.added_tweet_intent)));
        TurbineApp.get(context).inject(this);
        dataFuser.refresh();
    }

    @Override
    protected void onFinishInflate(){
        super.onFinishInflate();

        listView = (ListView) findViewById(R.id.main_list_view);
        listView.setAdapter(tweetAdapter);

    }

}
