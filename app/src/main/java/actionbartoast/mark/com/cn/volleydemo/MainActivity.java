package actionbartoast.mark.com.cn.volleydemo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import actionbartoast.mark.com.cn.volleydemo.actionbartoast.mark.com.cn.volleydemo.entity.RespFitment;

public class MainActivity extends ActionBarActivity {

    private EditText mEditText;
    private TextView mTextResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText = (EditText) findViewById(R.id.edit);
        mEditText.setText("http://www.baidu.com");
        mTextResult = (TextView) findViewById(R.id.text_result);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * string
     *
     * @param view
     */
    public void onBtnClick(View view) {
        RequestQueue newRequestQueue = Volley.newRequestQueue(MainActivity.this);
        StringRequest request = new StringRequest(mEditText.getText().toString().trim(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        mTextResult.setText(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                decodeVolleyError(error);
            }
        }
        );
        request.setTag(this);
        newRequestQueue.add(request);
    }

    /**
     * json
     *
     * @param view
     */
    public void onBtnClick1(View view) {
        RequestQueue newRequestQueue = Volley.newRequestQueue(MainApp.getContext());

        GsonRequest2<RespFitment> request = new GsonRequest2<RespFitment>(Request.Method.POST, "http://192.168.0.41:83/Api/SaleHouse/HouseDic",
                new Response.Listener<RespFitment>() {
                    @Override
                    public void onResponse(RespFitment response) {
                        mTextResult.setText(response.getDic().size() + "");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                decodeVolleyError(error);
            }
        }, RespFitment.class) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> mParams = new HashMap<String, String>();
                mParams.put("key", "Fitment");
                mParams.put("sign", "");
                return mParams;
            }
        };

        DefaultRetryPolicy r = new DefaultRetryPolicy(1,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        request.setRetryPolicy(r);
        newRequestQueue.add(request);
    }

    public void btnFrame(View view) {
        Map<String, String> mParams = new HashMap<String, String>();
        mParams.put("key", "Fitment");
        mParams.put("sign", "");

        NetTastContext.getInstance().getFitment(mParams, new Response.Listener<RespFitment>() {
            @Override
            public void onResponse(RespFitment respFitment) {
                mTextResult.setText(respFitment.getDic().size() + "");
            }
        }, RespFitment.class);

    }

    ProgressDialog p;

    public void btnpProgress(View view) {
        if (p == null) {
            p = new ProgressDialog(this);
        }

        if (p.isShowing()) {
            p.dismiss();
        } else {
            p.show();
        }
    }


    /**
     * 解析错误类型
     *
     * @param error
     */
    private void decodeVolleyError(VolleyError error) {
        long sss = error.getNetworkTimeMs();
        String msg = "time:" + sss;
        if (error.getCause() instanceof UnknownHostException) {
            msg += ", reson:" + "无法访问服务器";
        } else if (error.getCause() instanceof ConnectException) {
            msg += ", reson:" + "无法打开网络连接";
        } else if (error instanceof TimeoutError) {
            msg += ", reson:" + "连接超时";
        }
        Log.d("mark", error.toString());
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
        mTextResult.setText(error.toString());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.action_clear) {
            mTextResult.setText("");
            return true;
        }
        if (id == R.id.action_image) {
            startActivity(new Intent(this, PhotoActivity.class));
            return true;
        }

        //PhotoActivity
        return super.onOptionsItemSelected(item);
    }
}
