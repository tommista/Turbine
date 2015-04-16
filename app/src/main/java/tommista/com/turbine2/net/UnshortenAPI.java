package tommista.com.turbine2.net;

import retrofit.RestAdapter;
import tommista.com.turbine2.net.services.UnshortenService;

public class UnshortenAPI {

    private static final String SERVER_ADDRESS = "http://api.unshorten.it";
    private final RestAdapter restAdapter;

    public UnshortenService extensionService;

    public UnshortenAPI() {
        restAdapter = new RestAdapter.Builder()
                .setEndpoint(SERVER_ADDRESS)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        extensionService = restAdapter.create(UnshortenService.class);
    }
}