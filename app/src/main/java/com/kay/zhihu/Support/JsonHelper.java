package com.kay.zhihu.Support;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mac on 15-2-3.
 */
public class JsonHelper {
    public static List<News> parseJsonToList(String json) throws JSONException {
        JSONObject newsContent = new JSONObject(json);
        JSONArray newsArray = newsContent.getJSONArray("stories");
        List<News> newsList = new ArrayList<News>();
        for (int i = 0; i < newsArray.length(); i++) {
            JSONObject newsInJson = newsArray.getJSONObject(i);
            int id = newsInJson.optInt("id");
            String title = newsInJson.optString("title");
            String image = "";
            if (newsInJson.has("image")) {
                image = newsInJson.getJSONArray("image").getString(0);
            }
            News news = new News(id, title, image);
            newsList.add(news);
        }
        return newsList;
    }
}
