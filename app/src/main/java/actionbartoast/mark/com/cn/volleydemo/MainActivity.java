package actionbartoast.mark.com.cn.volleydemo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends ActionBarActivity {

    private EditText mEditText;
    private TextView mTextResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText = (EditText)findViewById(R.id.edit);
        mEditText.setText("http://www.baidu.com");
        mTextResult = (TextView)findViewById(R.id.text_result);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void onBtnClick(View view){
        RequestQueue newRequestQueue = Volley.newRequestQueue(MainActivity.this);

        StringRequest request = new StringRequest(mEditText.getText().toString().trim(),
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        mTextResult.setText(response);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError arg0) {
                Toast.makeText(getApplicationContext(),arg0.toString(),Toast.LENGTH_SHORT).show();
            }
        });
        request.setTag(this);

        newRequestQueue.add(request);

        //VolleyUtil.getQueue(getActivity()).add(request);

        //Toast.makeText(this,mEditText.getText().toString(),Toast.LENGTH_SHORT).show();
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

        return super.onOptionsItemSelected(item);
    }
}
