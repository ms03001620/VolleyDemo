package actionbartoast.mark.com.cn.volleydemo;

import java.lang.reflect.Type;

import android.net.Uri;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;


public class URIStringConvertor implements JsonSerializer<Uri>, JsonDeserializer<Uri> {

	public static final URIStringConvertor INSTANCE = new URIStringConvertor();

	@Override
	public Uri deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		if(json!=null){
			return Uri.parse(json.getAsString());
		}
		return null;
	}

	@Override
	public JsonElement serialize(Uri src, Type typeOfSrc, JsonSerializationContext context) {
        if (src != null) {  
            return new JsonPrimitive(src.toString());  
        }  
		return null;
	}



}
