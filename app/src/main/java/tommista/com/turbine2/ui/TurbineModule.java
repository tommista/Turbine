package tommista.com.turbine2.ui;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import tommista.com.turbine2.TurbineApp;

/**
 * Created by tbrown on 2/5/15.
 */

@Module(
        includes = {
        },
        injects = {
                TurbineApp.class, TurbineActivity.class
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

    @Provides @Singleton public AppContainer provideAppContainer() {
        return AppContainer.DEFAULT;
    }

}
