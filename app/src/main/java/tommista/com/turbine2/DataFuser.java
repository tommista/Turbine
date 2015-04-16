package tommista.com.turbine2;

import android.content.Context;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import timber.log.Timber;
import tommista.com.turbine2.models.Tweet;
import tommista.com.turbine2.models.UnshortenResponse;
import tommista.com.turbine2.net.TwitterAPI;
import tommista.com.turbine2.net.UnshortenAPI;

public class DataFuser {

    private Context context;
    private TwitterAPI twitterAPI;
    private UnshortenAPI unshortenAPI;
    private Handles handles;
    private Tweets tweets;


    public DataFuser(Context context, TwitterAPI twitterAPI, UnshortenAPI unshortenAPI, Handles handles, Tweets tweets){
        this.context = context;
        this.twitterAPI = twitterAPI;
        this.unshortenAPI = unshortenAPI;
        this.handles = handles;
        this.tweets = tweets;
    }

    public void refresh(){
        for(String handle : handles.getHandleList()){
            this.getTweetsForHandle(handle);
        }
    }

    public void getTweetsForHandle(final String handle){
        Timber.i("Fetching tweets for user %s", handle);
        twitterAPI.timelineService.getUserTimeline(handle, 30, new Callback<List<Tweet>>() {
            @Override
            public void success(List<Tweet> tweetList, Response response) {
                Timber.i("Successfully downloaded timeline for %s.", handle);

                for(final Tweet tweet : tweetList){
                    if(tweet.tweetEntities.urlList.length > 0){
                        String expandedURL = tweet.tweetEntities.urlList[0].expandedUrl;
                        if(expandedURL != null && expandedURL.length() > 0){

                            if(expandedURL.toLowerCase().contains("youtube") || expandedURL.toLowerCase().contains("spotify") || expandedURL.toLowerCase().contains("soundcloud")) {
                                if(!tweets.tweetExists(tweet)){
                                    Tweet newTweet = new Tweet(tweet);
                                    newTweet.handle = handle;
                                    newTweet.goodUrl = expandedURL;
                                    tweets.addTweet(newTweet);
                                }
                            }else{
                                unshortenAPI.extensionService.unshortenURL(expandedURL, context.getResources().getString(R.string.unshorten_api_key), "json", new Callback<UnshortenResponse>() {
                                    @Override
                                    public void success(UnshortenResponse unshortenResponse, Response response) {
                                        String url = unshortenResponse.fullUrl;

                                        if (url == null) {
                                            return;
                                        }

                                        if (url.toLowerCase().contains("youtube") || url.toLowerCase().contains("spotify") || url.toLowerCase().contains("soundcloud")) {
                                            if (!tweets.tweetExists(tweet)) {

                                                Tweet newTweet = new Tweet(tweet);
                                                newTweet.handle = handle;
                                                newTweet.goodUrl = url;
                                                tweets.addTweet(newTweet);
                                            }
                                        }
                                    }

                                    @Override
                                    public void failure(RetrofitError error) {

                                    }
                                });
                            }

                        }
                    }
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Timber.i("Failure downloading timeline for %s.", handle);
            }
        });
    }
}
