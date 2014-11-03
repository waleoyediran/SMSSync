package org.addhen.smssync.sway.api;

import android.content.Context;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import org.addhen.smssync.sway.util.AppConstants;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 *
 */
public class ServiceClient {
    private static ServiceClient instance;

    private RestAdapter mRestAdapter;
    private Map<String, Object> mClients = new HashMap<String, Object>();

    private String gBaseURL = AppConstants.PLACES_API_BASE;
    private String swayURL = AppConstants.SWAY_BASE;

    Gson gson;
    private RestAdapter swayRestAdapter;
    private Map<String, Object> swayClients = new HashMap<String, Object>();

    private ServiceClient() {
        gson = new GsonBuilder()
                .serializeNulls()
//                .setLongSerializationPolicy(new LongSerializationPolicy())
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
                    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                        return new Date(json.getAsJsonPrimitive().getAsLong());
                    }
                })
                .registerTypeAdapter(Date.class, new JsonSerializer<Date>() {
                    @Override
                    public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
                        return src == null ? null : new JsonPrimitive(String.valueOf(src.getTime()));
                    }
                })
                .create();
    }


    public static ServiceClient getInstance() {
        if (null == instance) {
            instance = new ServiceClient();
        }
        return instance;
    }

    @SuppressWarnings("unchecked")
    public <T> T getGoogleClient(Context context, Class<T> clazz) {
        if (mRestAdapter == null) {
            mRestAdapter = new RestAdapter.Builder()
                    .setEndpoint(getGoogleBaseURL(context))
                    .setConverter(new GsonConverter(gson))
                    .build();
        }
        T client = null;
        if ((client = (T) mClients.get(clazz.getCanonicalName())) != null) {
            return client;
        }
        client = mRestAdapter.create(clazz);
        mClients.put(clazz.getCanonicalName(), client);
        return client;
    }

    @SuppressWarnings("unchecked")
    public <T> T getSwayClient(Context context, Class<T> clazz) {
        if (swayRestAdapter == null) {
            swayRestAdapter = new RestAdapter.Builder()
                    .setEndpoint(getSwayURL(context))
                    .setConverter(new GsonConverter(gson))
                    .build();
        }
        T client = null;
        if ((client = (T) swayClients.get(clazz.getCanonicalName())) != null) {
            return client;
        }
        client = swayRestAdapter.create(clazz);
        swayClients.put(clazz.getCanonicalName(), client);
        return client;
    }

    public void setRestAdapter(RestAdapter restAdapter) {
        mRestAdapter = restAdapter;
    }

    public String getGoogleBaseURL(Context context) {
        // TODO: switch base url by some sort of settings logic
        return gBaseURL;
    }

    public String getSwayURL(Context context) {
        // TODO: switch base url by some sort of settings logic
        return swayURL;
    }

}
