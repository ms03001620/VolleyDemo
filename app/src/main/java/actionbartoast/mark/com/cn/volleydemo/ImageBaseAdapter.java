package actionbartoast.mark.com.cn.volleydemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;

public class ImageBaseAdapter extends BaseAdapter {
    private String[] imageUrlArray;
    private LayoutInflater inflater;

    private Context context;

    public ImageBaseAdapter(Context context, String[] imageUrlArray) {
        this.imageUrlArray = imageUrlArray;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public int getCount() {
        return imageUrlArray.length;
    }

    @Override
    public Object getItem(int position) {
        return imageUrlArray[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = this.inflater.inflate(R.layout.fr_image_request_list_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.ivCar = (ImageView) convertView.findViewById(R.id.iv_car);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String imageUrl = imageUrlArray[position];


        viewHolder.ivCar.setImageResource(R.drawable.ic_empty);
        VolleyUtil.getQueue(context).cancelAll(viewHolder.ivCar);
        ImageRequest request = new ImageRequest(imageUrl, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                viewHolder.ivCar.setImageBitmap(bitmap);
            }
        }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError arg0) {
                viewHolder.ivCar.setImageResource(R.drawable.ic_empty);
            }
        });
        request.setTag(viewHolder.ivCar);
        VolleyUtil.getQueue(this.context).add(request);


        return convertView;
    }

    static class ViewHolder {
        ImageView ivCar;
    }
}
