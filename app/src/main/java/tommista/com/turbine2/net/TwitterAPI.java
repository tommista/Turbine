package tommista.com.turbine2.net;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import tommista.com.turbine2.net.services.TimelineService;

public class TwitterAPI {
    private static final String SERVER_ADDRESS = "https://api.twitter.com";
    private final RestAdapter restAdapter;
    private RequestInterceptor reqInterceptor;

    public TimelineService timelineService;

    public Gson gson;

    public TwitterAPI() {

        gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        reqInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade requestFacade) {
                //requestFacade.addHeader("Authorization", "Bearer AAAAAAAAAAAAAAAAAAAAALXqdgAAAAAAMfVikYHyimgiQKPW9bqJGndfngk%3Dad7RyM7WUMG5knJcMm7PKICeoOLOfvORmqBZUlvvTVV6J3FI81");
                //requestFacade.addHeader("Authorization", "Basic Tk9yOXliM3V1TWcyakdFSlFuWTVzSjF0MDpuVGNqWVhMWFJ1UkwyY0U3ejlXMmRMWUxrakhaRlE3NFl0TVBDMnNIWUF3NXhSaHo2Rg==");
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
