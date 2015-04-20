package tommista.com.turbine2;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import tommista.com.turbine2.models.Tweet;

public class Tweets {

  private HashMap<String, Tweet> tweetMap;
  private ArrayList<Tweet> sortedList;
  private Context context;

  public Tweets(Context context) {
    tweetMap = new HashMap<>();
    sortedList = new ArrayList<>();
    this.context = context;
  }

  public void addTweet(Tweet tweet) {
    tweetMap.put(tweet.tweetId, tweet);
    sortedList.add(tweet);
    sort();
    sendTweetsChangedIntent();
  }

  public boolean tweetExists(Tweet tweet) {
    return (tweetMap.get(tweet.tweetId) != null);
  }

  public void removeTweet(Tweet tweet) {
    tweetMap.remove(tweet.tweetId);
    sortedList.remove(tweet);
    sort();
    sendTweetsChangedIntent();
  }

  public void removeTweetsByHandle(String handle) {

    ArrayList<Tweet> tempList = new ArrayList<>(tweetMap.values());

    for (Tweet tweet : tempList) {
      if (tweet.handle.compareTo(handle) == 0) {
        removeTweet(tweet);
      }
    }
  }

  public ArrayList<Tweet> getSortedList() {
    return sortedList;
  }

  private void sort() {
    Collections.sort(sortedList);
  }

  private void sendTweetsChangedIntent() {
    Intent intent = new Intent(context.getResources().getString(R.string.tweets_changed_intent));
    LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
  }
}
