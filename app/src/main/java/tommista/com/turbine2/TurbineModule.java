package tommista.com.turbine2;

import android.app.Application;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import timber.log.Timber;
import tommista.com.turbine2.adapters.HandleAdapter;
import tommista.com.turbine2.adapters.TweetAdapter;
import tommista.com.turbine2.net.TwitterAPI;
import tommista.com.turbine2.net.UnshortenAPI;
import tommista.com.turbine2.ui.Settings.SettingsView;
import tommista.com.turbine2.ui.Timeline.TimelineView;
import tommista.com.turbine2.util.StringPreference;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by tbrown on 2/5/15.
 */

@Module(
        includes = {
        },
        injects = {
                TurbineApp.class, TurbineActivity.class, TimelineView.class, SettingsView.class
        },
        complete = false,
        library = true
)

public class TurbineModule {
    private final TurbineApp app;

    public TurbineModule(TurbineApp app) {
        this.app = app;
    }

    @Provides @Singleton public Application provideApplication() {
        return app;
    }

    @Provides @Singleton SharedPreferences provideSharedPreferences(Application app) {
        return app.getSharedPreferences("turbine", MODE_PRIVATE);
    }

    @Provides @Singleton public Handles providesHandles(@SavedHandlesPreference StringPreference stringPreference){
        return new Handles(stringPreference);
    }

    @Provides @Singleton public Tweets providesTweets(){
        return new Tweets();
    }

    @Provides @Singleton public TwitterAPI providesTwitterAPI(Application application){
        return new TwitterAPI(application.getApplicationContext());
    }

    @Provides @Singleton public UnshortenAPI providesUnshortenAPI(Application application){
        return new UnshortenAPI(application);
    }

    @Provides @Singleton @SavedHandlesPreference public StringPreference providesSavedHandlesPreference(Application application, SharedPreferences preferences){
        return new StringPreference(preferences, application.getResources().getString(R.string.saved_handles_list_key));
    }

    @Provides public HandleAdapter providesHandleAdapter(Application application, Handles handles){
        return new HandleAdapter(application, handles);
    }

    @Provides public TweetAdapter providesTweetAdapter(Application application, Tweets tweets, Picasso picasso){
        return new TweetAdapter(application, tweets, picasso);
    }

    @Provides @Singleton OkHttpClient provideOkHttpClient(Application app) {
        return createOkHttpClient(app);
    }

    @Provides @Singleton Picasso providePicasso(Application app, OkHttpClient client) {
        return new Picasso.Builder(app)
                .downloader(new OkHttpDownloader(client))
                .listener(new Picasso.Listener() {
                    @Override public void onImageLoadFailed(Picasso picasso, Uri uri, Exception e) {
                        Timber.e(e, "Failed to load image: %s", uri);
                    }
                })
                .build();
    }

    @Provides @Singleton DataFuser providesDataFuser(Application application, TwitterAPI twitterAPI, UnshortenAPI unshortenAPI, Handles handles, Tweets tweets){
        return new DataFuser(application, twitterAPI, unshortenAPI, handles, tweets);
    }

    @Provides @Singleton @IcomoonFont Typeface providesIcomoonFont(Application application){
        return Typeface.createFromAsset(application.getAssets(), "icomoon.ttf");
    }

    @Retention(RetentionPolicy.RUNTIME) @Qualifier public @interface SavedHandlesPreference{}
    @Retention(RetentionPolicy.RUNTIME) @Qualifier public @interface IcomoonFont{}

    static OkHttpClient createOkHttpClient(Application app) {
        OkHttpClient client = new OkHttpClient();
        return client;
    }

}
