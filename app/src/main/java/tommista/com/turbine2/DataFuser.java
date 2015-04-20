package tommista.com.turbine2;

import android.content.Context;
import java.util.List;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import timber.log.Timber;
import tommista.com.turbine2.models.Tweet;
import tommista.com.turbine2.models.UnshortenResponse;
import tommista.com.turbine2.net.TimelineService;
import tommista.com.turbine2.net.UnshortenService;

public class DataFuser {

  private Context context;
  private TimelineService timelineService;
  private UnshortenService unshortenService;
  private Handles handles;
  private Tweets tweets;

  public DataFuser(Context context, TimelineService timelineService,
      UnshortenService unshortenService, Handles handles, Tweets tweets) {
    this.context = context;
    this.timelineService = timelineService;
    this.unshortenService = unshortenService;
    this.handles = handles;
    this.tweets = tweets;
  }

  public void refresh() {
    for (String handle : handles.getHandleList()) {
      this.getTweetsForHandle(handle);
    }
  }

  public void getTweetsForHandle(final String handle) {
    Timber.i("Fetching tweets for user %s", handle);
    timelineService.getUserTimeline(handle, 30, new Callback<List<Tweet>>() {
      @Override
      public void success(List<Tweet> tweetList, Response response) {
        successfullyRetrievedTweetsForUser(handle, tweetList);
      }

      @Override
      public void failure(RetrofitError error) {
        Timber.i("Failure downloading timeline for %s.", handle);
      }
    });
  }

  private void successfullyRetrievedTweetsForUser(final String handle, List<Tweet> tweetList) {
    Timber.i("Successfully downloaded timeline for %s.", handle);

    for (final Tweet tweet : tweetList) {
      if (tweet.tweetEntities.urlList.length > 0) {
        final String expandedURL = tweet.tweetEntities.urlList[0].expandedUrl;
        if (expandedURL != null && expandedURL.length() > 0) {

          if (urlContainsTerms(expandedURL)) {
            if (!tweets.tweetExists(tweet)) {
              Tweet newTweet = new Tweet(tweet);
              newTweet.handle = handle;
              newTweet.goodUrl = expandedURL;
              tweets.addTweet(newTweet);
            }
          } else {
            unshortenURL(expandedURL, handle, tweet);
          }
        }
      }
    }
  }

  private void successfullyUnshortenedURL(final String handle, Tweet tweet,
      UnshortenResponse unshortenResponse) {
    String url = unshortenResponse.fullUrl;

    if (url == null) {
      return;
    }

    if (urlContainsTerms(url)) {
      if (!tweets.tweetExists(tweet)) {

        Tweet newTweet = new Tweet(tweet);
        newTweet.handle = handle;
        newTweet.goodUrl = url;
        tweets.addTweet(newTweet);
      }
    }
  }

  private void unshortenURL(final String expandedURL, final String handle, final Tweet tweet) {
    unshortenService.unshortenURL(expandedURL,
        context.getResources().getString(R.string.unshorten_api_key), "json",
        new Callback<UnshortenResponse>() {
          @Override
          public void success(UnshortenResponse unshortenResponse, Response response) {
            successfullyUnshortenedURL(handle, tweet, unshortenResponse);
          }

          @Override
          public void failure(RetrofitError error) {
            Timber.i("Failure unshortening url: " + expandedURL);
          }
        });
  }

  private boolean urlContainsTerms(String url) {
    return (url.toLowerCase().contains("youtube")
        || url.toLowerCase().contains("spotify")
        || url.toLowerCase().contains("soundcloud"));
  }
}
