package tommista.com.turbine2.net;

import java.util.List;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;
import tommista.com.turbine2.models.Tweet;

public interface TimelineService {

  @GET("/1.1/statuses/user_timeline.json") void getUserTimeline(
      @Query("screen_name") String screenName, @Query("count") int count,
      Callback<List<Tweet>> callback);
}