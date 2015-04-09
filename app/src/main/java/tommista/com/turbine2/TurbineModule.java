package tommista.com.turbine2;

import android.app.Application;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;

import javax.inject.Qualifier;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import tommista.com.turbine2.models.Handle;
import tommista.com.turbine2.net.TwitterAPI;
import tommista.com.turbine2.ui.ActivityHierarchyServer;
import tommista.com.turbine2.ui.Settings.SettingsView;
import tommista.com.turbine2.ui.Timeline.TimelineView;

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

    @Provides @Singleton public ActivityHierarchyServer provideActivityHierarchyServer() {
        return ActivityHierarchyServer.NONE;
    }

    @Provides @Singleton @HandleList public ArrayList<Handle> providesHandleList(){
        ArrayList<Handle> handleList = new ArrayList<>();
        handleList.add(new Handle("@asdfasdf"));
        handleList.add(new Handle("@qwerqwer"));
        return handleList;
    }

    @Provides @Singleton public TwitterAPI providesTwitterAPI(Application application){
        return new TwitterAPI(application.getApplicationContext());
    }

    @Retention(RetentionPolicy.RUNTIME) @Qualifier public @interface HandleList {}

}
