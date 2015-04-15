package tommista.com.turbine2;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import timber.log.Timber;
import tommista.com.turbine2.models.Tweet;
import tommista.com.turbine2.net.TwitterAPI;

public class DataFuser {

    private TwitterAPI twitterAPI;
    private Handles handles;
    private Tweets tweets;


    public DataFuser(TwitterAPI twitterAPI, Handles handles, Tweets tweets){
        this.twitterAPI = twitterAPI;
        this.handles = handles;
        this.tweets = tweets;
    }

    public void getTweetsForHandle(final String handle){
        Timber.i("Fetching tweets for user %s", handle);
        twitterAPI.timelineService.getUserTimeline(handle, 30, new Callback<List<Tweet>>() {
            @Override
            public void success(List<Tweet> tweetList, Response response) {
                Timber.i("Successfully downloaded timeline for %s.", handle);

                for(Tweet tweet : tweetList){
                    Timber.i(tweet.toString());
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Timber.i("Failure downloading timeline for %s.", handle);
            }
        });
    }
}
