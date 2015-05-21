package actionbartoast.mark.com.cn.volleydemo;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.util.Map;

import actionbartoast.mark.com.cn.marklibrary.BasePostRequest;
import actionbartoast.mark.com.cn.marklibrary.ContentMapper;

public class GsonRequest<T> extends BasePostRequest<T> {
    private final Response.Listener<T> mListener;
    protected Class<T> mResponseType;

    public GsonRequest(String url, Response.Listener<T> listener, Class<T> responseType, Map<String, String> param) {
        this(url, listener, null, responseType, param);
    }

    public GsonRequest(String url, Response.Listener<T> listener, Response.ErrorListener errorListener, Class<T> responseType, Map<String, String> param) {
        super(url, listener, errorListener, param);
        this.mListener = listener;
        this.mResponseType = responseType;
    }

    protected void deliverResponse(T response) {
        this.mListener.onResponse(response);
    }

    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        String parsed;

        try {
            Thread.sleep(3000);
            parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            Gson gson = ContentMapper.getDefaultContentMapper().getGson();
            T t = gson.fromJson(parsed, mResponseType);
            return Response.success(t, HttpHeaderParser.parseCacheHeaders(response));

        } catch (Exception var4) {
            parsed = new String(response.data);
        }
        return null;
    }
}
