package tommista.com.turbine2.net;

import android.content.Context;

import retrofit.RestAdapter;
import tommista.com.turbine2.R;
import tommista.com.turbine2.net.services.UnshortenService;

public class UnshortenAPI {

    private final RestAdapter restAdapter;

    public UnshortenService extensionService;

    public UnshortenAPI(Context context) {
        restAdapter = new RestAdapter.Builder()
                .setEndpoint(context.getResources().getString(R.string.unshorten_api_endpoint))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        extensionService = restAdapter.create(UnshortenService.class);
    }
}