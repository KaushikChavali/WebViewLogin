package com.example.admin.webviewcomplete;

/**
 * Created by admin on 8/16/2014.
 */


        import org.json.JSONArray;
        import org.json.JSONObject;

        import java.util.ArrayList;
        import java.util.List;

/**
 * Created by admin on 7/21/2014.
 */
public class SiteJSONParser {
    public static List<Site> parseFeed(String content){

        try{
            JSONObject jsonObj = new JSONObject(content);

            // Getting JSON Array node
            JSONArray links = jsonObj.getJSONArray("links");
            List<Site> siteList = new ArrayList<Site>();

            // looping through All Contacts
            for (int i=0; i<links.length(); i++){

                JSONObject obj = links.getJSONObject(i);
                Site site = new Site();

                site.setId(obj.getInt("id"));
                site.setUser_id(obj.getInt("user_id"));
                site.setUrl(obj.getString("url"));
                site.setDescription(obj.getString("description"));
                site.setCreated_at(obj.getString("created_at"));
                site.setUpdated_at(obj.getString("updated_at"));

                siteList.add(site);
            }
            return siteList;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
}
