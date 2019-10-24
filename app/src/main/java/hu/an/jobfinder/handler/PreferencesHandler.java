package hu.an.jobfinder.handler;

import android.content.Context;
import android.content.SharedPreferences;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

import hu.an.jobfinder.model.JobItem;

public class PreferencesHandler {

    private static final String PREFERENCES_NAME = "JobFinder";
    public static final String KEY_FAVORITE_LIST = "KeyFavoriteList";

    private SharedPreferences mSharedPref;
    private ObjectMapper mMapper;

    private static PreferencesHandler mInstance;

    private PreferencesHandler(Context context) {
        mSharedPref = context.getSharedPreferences(PREFERENCES_NAME, 0);
        mMapper = new ObjectMapper();
    }

    public static void initHandler(Context context) {
        mInstance = new PreferencesHandler(context);
    }

    public static PreferencesHandler getInstance() {
        return mInstance;
    }

    public boolean getBooleanField(String key, boolean defaultValue) {
        return mSharedPref.getBoolean(key, defaultValue);
    }

    public void setBooleanField(String key, boolean value) {
        mSharedPref.edit().putBoolean(key, value).apply();
    }

    public int getIntField(String key, int defaultValue) {
        return mSharedPref.getInt(key, defaultValue);
    }

    public void setIntField(String key, int value) {
        mSharedPref.edit().putInt(key, value).apply();
    }

    public String getStringField(String key, String defaultValue) {
        return mSharedPref.getString(key, defaultValue);
    }

    public void setStringField(String key, String value) {
        mSharedPref.edit().putString(key, value).apply();
    }

    public List<JobItem> getJobList(String key) {
        String serialized = mSharedPref.getString(key, "");
        if (serialized != null) {
            try {
                return mMapper.readValue(serialized, new TypeReference<List< JobItem >>() {
                });
            } catch (Exception ignore) {}
        }
        return new ArrayList<>();
    }

    public void setJobList(String key, List<JobItem> listValue) {
        try {
            mSharedPref.edit().putString(key, mMapper.writeValueAsString(listValue)).apply();
        } catch (JsonProcessingException ignore) {}
    }
}
