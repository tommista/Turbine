package tommista.com.turbine2.net.services;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;
import tommista.com.turbine2.models.UnshortenResponse;

public interface UnshortenService {
    @GET("/")
    void unshortenURL(@Query("shortURL") String shortURL, @Query("apiKey") String apiKey, @Query("responseFormat") String responseFormat, Callback<UnshortenResponse> callback);
}