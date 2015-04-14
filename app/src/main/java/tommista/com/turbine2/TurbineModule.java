package tommista.com.turbine2;

import android.app.Application;
import android.content.SharedPreferences;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.PriorityQueue;

import javax.inject.Qualifier;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import tommista.com.turbine2.models.Handle;
import tommista.com.turbine2.models.Tweet;
import tommista.com.turbine2.net.TwitterAPI;
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

    @Provides @Singleton @HandleList public ArrayList<Handle> providesHandleList(){
        ArrayList<Handle> handleList = new ArrayList<>();
        handleList.add(new Handle("@asdfasdf"));
        handleList.add(new Handle("@qwerqwer"));
        return handleList;
    }

    @Provides @Singleton @TweetList public PriorityQueue<Tweet> providesTweetList(){
        return new PriorityQueue<>();
    }

    @Provides @Singleton public TwitterAPI providesTwitterAPI(Application application){
        return new TwitterAPI(application.getApplicationContext());
    }

    @Provides @Singleton @SavedHandlesPreference public StringPreference providesSavedHandlesPreference(Application application, SharedPreferences preferences){
        return new StringPreference(preferences, application.getResources().getString(R.string.saved_handles_list_key));
    }

    @Retention(RetentionPolicy.RUNTIME) @Qualifier public @interface HandleList {}
    @Retention(RetentionPolicy.RUNTIME) @Qualifier public @interface TweetList {}
    @Retention(RetentionPolicy.RUNTIME) @Qualifier public @interface SavedHandlesPreference{}

}
