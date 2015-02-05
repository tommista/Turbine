package tommista.com.turbine2;

import android.app.Application;
import android.content.Context;

import javax.inject.Inject;

import dagger.ObjectGraph;
import timber.log.Timber;
import tommista.com.turbine2.ui.ActivityHierarchyServer;
import tommista.com.turbine2.ui.TurbineModule;

/**
 * Created by tbrown on 2/5/15.
 */
public class TurbineApp extends Application{

    @Inject ActivityHierarchyServer activityHierarchyServer;

    private ObjectGraph objectGraph;

    public static TurbineApp get(Context context) {
        return (TurbineApp) context.getApplicationContext();
    }

    @Override public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            // TODO eventually put remote logging into a tree and put here.
        }

        buildObjectGraphAndInject();

        //rootScope = Mortar.createRootScope(BuildConfig.DEBUG);

        registerActivityLifecycleCallbacks(activityHierarchyServer);
    }

    public void buildObjectGraphAndInject() {
        objectGraph = ObjectGraph.create(new TurbineModule(this));
        objectGraph.inject(this);
    }

    public void inject(Object o) {
        objectGraph.inject(o);
    }

    /*public MortarScope getRootScope() {
        return rootScope;
    }*/
}
