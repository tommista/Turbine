package tommista.com.turbine2.ui.Timeline;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.support.v4.content.LocalBroadcastManager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.squareup.picasso.Picasso;
import javax.inject.Inject;
import tommista.com.turbine2.DataFuser;
import tommista.com.turbine2.R;
import tommista.com.turbine2.TurbineApp;
import tommista.com.turbine2.Tweets;
import tommista.com.turbine2.adapters.TweetAdapter;

import static tommista.com.turbine2.TurbineModule.IconFont;

public class TimelineView extends LinearLayout {

  private Context context;
  private ListView listView;
  private Button settingsButton;
  private TweetAdapter tweetAdapter;

  private BroadcastReceiver newTweetReceiver = new BroadcastReceiver() {
    @Override
    public void onReceive(Context context, Intent intent) {
      receivedNewTweetIntent(intent);
    }
  };

  @Inject Tweets tweets;
  @Inject DataFuser dataFuser;
  @Inject @IconFont Typeface font;
  @Inject Picasso picasso;

  public TimelineView(Context context, AttributeSet attrs) {
    super(context, attrs);
    this.context = context;
    LocalBroadcastManager.getInstance(context)
        .registerReceiver(newTweetReceiver,
            new IntentFilter(context.getResources().getString(R.string.tweets_changed_intent)));
    TurbineApp.get(context).inject(this);

    tweetAdapter = new TweetAdapter(context, tweets, picasso);
    dataFuser.refresh();
  }

  @Override
  protected void onFinishInflate() {
    super.onFinishInflate();

    settingsButton = (Button) findViewById(R.id.settings_button);
    settingsButton.setTypeface(font);
    settingsButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        settingsButtonPressed();
      }
    });

    listView = (ListView) findViewById(R.id.main_list_view);
    listView.setAdapter(tweetAdapter);
  }

  private void receivedNewTweetIntent(Intent intent) {
    tweetAdapter.notifyDataSetChanged();
  }

  private void settingsButtonPressed() {
    ((Activity) context).setContentView(R.layout.settings_view);
  }
}
