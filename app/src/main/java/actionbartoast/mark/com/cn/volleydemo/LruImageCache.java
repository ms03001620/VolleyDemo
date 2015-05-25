package actionbartoast.mark.com.cn.volleydemo;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.toolbox.ImageLoader.ImageCache;

public class LruImageCache implements ImageCache{

	private LruCache<String, Bitmap> lruCache;  
	  
    public LruImageCache() {  
        int maxSize = 20 * 1024 * 1024;
        lruCache = new LruCache<String, Bitmap>(maxSize) {  
            @Override  
            protected int sizeOf(String key, Bitmap bitmap) {  
                return bitmap.getRowBytes() * bitmap.getHeight();  
            }  
        };  
    }  
  
    @Override  
    public Bitmap getBitmap(String url) {  
        return lruCache.get(url);  
    }  
  
    @Override  
    public void putBitmap(String url, Bitmap bitmap) {  
    	lruCache.put(url, bitmap);  
    }  

}
