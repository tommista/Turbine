package tommista.com.turbine2;

import android.app.Application;
import android.content.Context;
import dagger.ObjectGraph;
import timber.log.Timber;

public class TurbineApp extends Application {

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
  }

  public void buildObjectGraphAndInject() {
    objectGraph = ObjectGraph.create(new TurbineModule(this));
    objectGraph.inject(this);
  }

  public void inject(Object o) {
    objectGraph.inject(o);
  }
}
