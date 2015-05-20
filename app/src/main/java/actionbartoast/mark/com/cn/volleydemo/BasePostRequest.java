package actionbartoast.mark.com.cn.volleydemo;

import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;

import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/5/20.
 */
public abstract class BasePostRequest<T> extends Request<T> {

    private Map<String, String> params;

    public BasePostRequest(String url, Response.Listener<T> listener, Response.ErrorListener errorListener, Map<String, String> params) {
        super(Request.Method.POST, url, errorListener==null? new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                BasePostRequest.decodeVolleyError(error);
            }
        }:errorListener);

        this.params = params;
        if(this.params==null){
            this.params = new HashMap<String, String>();
        }
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return params;
    }

    public static void decodeVolleyError(VolleyError error){
        long timeMs = error.getNetworkTimeMs();
        String msg = "time passed:"+timeMs;
        if(error.getCause() instanceof UnknownHostException){
            msg+=", reson:"+"无法访问服务器";
        }else if(error.getCause() instanceof ConnectException){
            msg+=", reson:"+"无法打开网络连接";
        }else if(error instanceof TimeoutError){
            msg+=", reson:"+"连接超时";
        }
        Toast.makeText(MainApp.getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public DefaultRetryPolicy getDefaultRetryPolicy(){
        DefaultRetryPolicy r = new DefaultRetryPolicy(30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        return r;
    }
}
