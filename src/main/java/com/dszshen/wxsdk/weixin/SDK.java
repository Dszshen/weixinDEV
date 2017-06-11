package com.dszshen.wxsdk.weixin;

import com.belerweb.social.bean.Error;
import com.belerweb.social.bean.Result;
import com.belerweb.social.exception.SocialException;
import com.belerweb.social.http.Http;
import com.belerweb.social.http.HttpException;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangbin on 2017/5/27 0027.
 */
public abstract class SDK {
    public SDK() {
    }

    public String get(String url, List<NameValuePair> params) {
        try {
            return Http.get(url, params);
        } catch (HttpException var4) {
            throw new SocialException(var4);
        }
    }

    public String get(String url) {
        return this.get(url, (List)null);
    }

    public String post(String url, HttpEntity postBody) {
        try {
            return Http.post(url, postBody, new Header[0]);
        } catch (HttpException var4) {
            throw new SocialException(var4);
        }
    }

    public String post(String url, List<NameValuePair> params) {
        try {
            return Http.post(url, params, "UTF-8", new Header[0]);
        } catch (HttpException var4) {
            throw new SocialException(var4);
        }
    }

    public String post(String url) {
        return this.post(url, (HttpEntity)null);
    }

    public void addParameter(List<NameValuePair> params, String name, Object value) {
        if(value == null) {
            throw new SocialException("Parameter " + name + " must not be null.");
        } else {
            params.add(new BasicNameValuePair(name, value.toString()));
        }
    }

    public void addNotNullParameter(List<NameValuePair> params, String name, Object value) {
        if(value != null) {
            params.add(new BasicNameValuePair(name, value.toString()));
        }

    }

    public void addTrueParameter(List<NameValuePair> params, String name, Boolean value) {
        if(Boolean.TRUE.equals(value)) {
            params.add(new BasicNameValuePair(name, value.toString()));
        }

    }

    public Result<String> lonLatToAddress(Double lon, Double lat) {
        List<NameValuePair> params = new ArrayList();
        this.addParameter(params, "sensor", "false");
        this.addParameter(params, "language", "zh");
        this.addParameter(params, "latlng", lat + "," + lon);
        String json = this.get("https://maps.googleapis.com/maps/api/geocode/json", params);
        JSONObject jsonObject = new JSONObject(json);
        if(!"OK".equals(jsonObject.getString("status"))) {
            Error error = new Error();
            error.setErrorCode(jsonObject.getString("status"));
            error.setError(jsonObject.optString("error_message"));
            return new Result(error);
        } else {
            JSONArray results = jsonObject.getJSONArray("results");
            return results.length() == 0?new Result(""):new Result(results.getJSONObject(0).getString("formatted_address"));
        }
    }
}
