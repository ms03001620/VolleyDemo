package actionbartoast.mark.com.cn.marklibrary;

import android.database.Cursor;
import android.net.Uri;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ContentMapper {
	private static ContentMapper defaultContentMapper;
	private static Gson defaultGson;
	private static GsonBuilder gsonBuilder;
	
	public static synchronized ContentMapper getDefaultContentMapper() {
		if (defaultContentMapper == null) {
			ContentMapper mapper = new ContentMapper();
			defaultContentMapper = mapper;
			gsonBuilder = new GsonBuilder(); 
			gsonBuilder.registerTypeAdapter(Uri.class, URIStringConvertor.INSTANCE);
			defaultGson = gsonBuilder.create();
		}
		return defaultContentMapper;
	}
	
	public Gson getGson(){
		return defaultGson;
	}
	
	public static void registerTypeAdapter(Type type, Object typeAdapter){
		gsonBuilder.registerTypeAdapter(type, typeAdapter);
		defaultGson = gsonBuilder.create();
	}
	
	public <T> List<T> toObjectList(Class<? extends T> cls, Cursor c) {
		List<T> list = new ArrayList<T>();
		c.moveToPosition(-1);
		while (c.moveToNext()) {
			T bean = toObject(cls, c);
			list.add(bean);
		}
		return list;
	}
	
	
	public <T> T toObject(Class<? extends T> cls, Cursor c) {
		T bean = null;
		int id = c.getColumnIndexOrThrow("json");
		String json = c.getString(id);
		bean = defaultGson.fromJson(json, cls);
		return bean;
	}

	
}
