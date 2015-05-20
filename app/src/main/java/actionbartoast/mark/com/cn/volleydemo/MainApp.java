package actionbartoast.mark.com.cn.volleydemo;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2015/5/20.
 */
public class MainApp extends Application{

    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
    }

    public static Context getContext() {
        return sContext;
    }

}
