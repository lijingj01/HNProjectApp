package as.hn.com.hnprojectapp;

import android.app.Application;

import com.hn.business.Tools.CrashHandler;

public class CrashApplication extends Application {

    @Override

    public void onCreate() {

        super.onCreate();

        CrashHandler crashHandler = CrashHandler.getInstance();

        crashHandler.init(getApplicationContext());

    }

}
