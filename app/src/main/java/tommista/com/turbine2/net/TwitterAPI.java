package tommista.com.turbine2.net;

import android.content.Context;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import tommista.com.turbine2.R;
import tommista.com.turbine2.net.services.TimelineService;

public class TwitterAPI {
    private static final String SERVER_ADDRESS = "https://api.twitter.com";
    private final RestAdapter restAdapter;
    private RequestInterceptor reqInterceptor;

    public TimelineService timelineService;

    public TwitterAPI(final Context context) {

        reqInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade requestFacade) {
                requestFacade.addHeader("Authorization", "Bearer " + context.getResources().getString(R.string.twitter_token));
            }
        };

        restAdapter = new RestAdapter.Builder()
                .setEndpoint(SERVER_ADDRESS)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setRequestInterceptor(reqInterceptor)
                .build();

        timelineService = restAdapter.create(TimelineService.class);
    }
}