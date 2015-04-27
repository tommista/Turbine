package tommista.com.turbine2.net;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;
import tommista.com.turbine2.models.UnshortenResponse;

// Retrofit interfae for talking to the unshorten.it REST API

public interface UnshortenService {
  @GET("/") void unshortenURL(@Query("shortURL") String shortURL, @Query("apiKey") String apiKey,
      @Query("responseFormat") String responseFormat, Callback<UnshortenResponse> callback);
}