package actionbartoast.mark.com.cn.volleydemo;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.HashMap;
import java.util.Map;

import actionbartoast.mark.com.cn.marklibrary.VolleyBus;

/**
 * Created by Administrator on 2015/5/20.
 */
public class NetTastContext {
    private static NetTastContext instance;
    private VolleyBus bus;

    private NetTastContext(){
        bus = VolleyBus.getInstance();
    }

    public static NetTastContext getInstance() {
        if (instance == null) {
            instance = new NetTastContext();
        }
        return instance;
    }

    public <T> void getFitment(Map<String, String> param, Response.Listener listener, Class<T> responseType) {
        GsonRequest<T> request = new GsonRequest<T>("http://192.168.0.41:83/Api/SaleHouse/HouseDic",listener, null,responseType, param);
        bus.addToRequestQueue(request);
    }
}
