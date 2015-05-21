package actionbartoast.mark.com.cn.volleydemo;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;

import actionbartoast.mark.com.cn.marklibrary.ContentMapper;

public class GsonRequest2<T> extends Request<T> {
    private final Response.Listener<T> mListener;
    protected Class<T> mResponseType;

    public GsonRequest2(int method, String url, Response.Listener<T> listener, Response.ErrorListener errorListener, Class<T> responseType) {
        super(method, url, errorListener);
        this.mListener = listener;
        this.mResponseType = responseType;
    }

    protected void deliverResponse(T response) {
        this.mListener.onResponse(response);
    }

    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        String parsed;
        T t;
        try {
            parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));

            Gson gson = ContentMapper.getDefaultContentMapper().getGson();

            t = gson.fromJson(parsed, mResponseType);

            return Response.success(t, HttpHeaderParser.parseCacheHeaders(response));

        } catch (UnsupportedEncodingException var4) {
            parsed = new String(response.data);
        }

        //return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
        return null;
    }
}
